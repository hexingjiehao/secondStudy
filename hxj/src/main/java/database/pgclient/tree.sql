-- PostgreSQL 递归查询 - 树型数据按路径分组输出
-- 参考文档：https://blog.csdn.net/weixin_34246551/article/details/90584507

----------------------------------------------- Miscellaneous Definition -----------------------------------------------
-- 创建树扩展
CREATE EXTENSION ltree;


--------------------------------------------------- Table Definition ---------------------------------------------------
-- 创建有树类型字段的表，这个path字段的格式是0.1.2等id做成的字符串
CREATE TABLE department
(
  department_id BIGSERIAL,
  title         TEXT        NOT NULL,
  path          LTREE,
  created_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

  -- 添加path字段的约束，最大长度限制
  CONSTRAINT path_max_length CHECK (length(ltree2text(path)) < 2048),
  PRIMARY KEY (department_id)
);


--------------------------------------------------- Index Definition ---------------------------------------------------
-- 创建path字段的索引
CREATE INDEX department_path_gist_idx ON department USING gist (path);


-------------------------------------------------- Function Definition -------------------------------------------------
-- 检查部门是否存在
CREATE OR REPLACE FUNCTION check_department_exists(v_department BIGINT)
  RETURNS VOID AS
$$
BEGIN
  PERFORM 1 FROM department WHERE department_id = v_department;
  IF NOT FOUND THEN
    RAISE EXCEPTION '404: department not found';
  END IF;
END;
$$
LANGUAGE PLPGSQL;


-- 检查并获取部门的path字段
CREATE OR REPLACE FUNCTION check_and_get_department_path(v_department BIGINT)
    RETURNS LTREE AS
$$
DECLARE
    v_path LTREE;
BEGIN
    SELECT path INTO v_path FROM department WHERE department_id = v_department;
    IF NOT found THEN
        RAISE EXCEPTION '404: department not found';
    END IF;
    RETURN v_path;
END;
$$
LANGUAGE PLPGSQL;


-- 检查部门的标题是否唯一，在进行一系列修改操作时
-- subpath(ltree,offset,len):表示从某个位置开始，截取的长度。如果为负数，从右边开始，直到最后位置
CREATE OR REPLACE FUNCTION check_department_title_unique(v_department BIGINT, v_parent_path LTREE, v_title TEXT)
    RETURNS VOID AS
$$
BEGIN
    IF v_department > 0 THEN
        -- 父类
        PERFORM 1 FROM department WHERE v_parent_path = subpath(path, 0, -1) AND department_id != v_department AND title = v_title;
    ELSE
        -- 检查是否有的部门的上级部门和待创建的部门的上级部门一致，然后检查子部门title是否已经有了
        PERFORM 1 FROM department WHERE v_parent_path = subpath(path, 0, -1) AND title = v_title;
    END IF;
    IF found THEN
        RAISE EXCEPTION '400: 所属上级部门已存在部门名称：%', v_title;
    END IF;
END;
$$
LANGUAGE PLPGSQL;


-- 移动部门,给定新部门的路径
CREATE OR REPLACE FUNCTION move_department(v_department BIGINT, v_parent_path LTREE)
    RETURNS INT AS
$$
DECLARE
    current_path LTREE;
BEGIN
    SELECT path INTO STRICT current_path FROM department WHERE department_id = v_department;

    -- 检查新的部门是否是自己的子部门，<@有可能是包含关系。找到新部门，并且判断新部门的path是否左包含旧部门的全部
    PERFORM 1
    FROM department
    WHERE path <@ current_path
      AND path = v_parent_path;
    IF found THEN
        RAISE EXCEPTION '400: 无法将部门设为其自身或子部门的子部门';
    END IF;

    -- 设置部门的新路径，将子路径一并修改。将左包含就部门路径的所有部门进行修改
    -- nlevel():表示的是获取树节点路径的长度
    UPDATE department
    SET updated_at = CURRENT_TIMESTAMP,
        path       = v_parent_path || subpath(path, nlevel(current_path) - 1)
    WHERE path <@ current_path;

    RETURN 0;
END;
$$
LANGUAGE PLPGSQL;


-- 创建部门
CREATE OR REPLACE FUNCTION create_department(v_json JSONB)
    RETURNS BIGINT AS
$$
DECLARE
    v_title       TEXT := v_json ->> 'title';
    v_parent_path LTREE;
    seq_id        BIGINT;
BEGIN
    -- 检查待创建部门的上级部门是否存在，并获取上级部门path
    v_parent_path := check_and_get_department_path(CAST(v_json ->> 'parent_id' AS BIGINT));
    -- 检查部门标题是否唯一
    PERFORM check_department_title_unique(-1, v_parent_path, v_title);

    INSERT INTO department (title)
    VALUES (v_title)
    RETURNING department_id
        INTO seq_id;

    -- 修改部门path，||符号表示字符串连接符
    UPDATE department
    SET updated_at = CURRENT_TIMESTAMP,
        path       = v_parent_path || seq_id :: TEXT
    WHERE department_id = seq_id;

    RETURN seq_id;
END;
$$
LANGUAGE PLPGSQL;


-- 修改部门
CREATE OR REPLACE FUNCTION update_department(v_department BIGINT, v_json JSONB)
    RETURNS INT AS
$$
DECLARE
    v_title       TEXT := v_json ->> 'title';
    v_parent_path LTREE;
BEGIN
    PERFORM check_department_exists(v_department);


    IF v_department > 0 THEN
        -- 这里检查并获取部门的父类部门path
        v_parent_path := check_and_get_department_path(CAST(v_json ->> 'parent_id' AS BIGINT));

        -- 检查新创建的部门和已经创建的同等级部门是否同名
        PERFORM check_department_title_unique(v_department, v_parent_path, v_title);
    END IF;

    UPDATE department
    SET updated_at  = CURRENT_TIMESTAMP,
        title       = v_title
    WHERE department_id = v_department;

    -- 根节点不能被移动
    IF v_department = 0 THEN
        RETURN 0;
    ELSE
        RETURN move_department(v_department, v_parent_path);
    END IF;
END;
$$
LANGUAGE PLPGSQL;


-- 移动部门到父类层级
CREATE OR REPLACE FUNCTION move_department_to_parent(v_department BIGINT, v_parent BIGINT)
    RETURNS BIGINT AS
$$
DECLARE
    v_parent_path LTREE;
    v_title       TEXT;
BEGIN
    IF v_department = 0 THEN
        RETURN 0;
    END IF;

    -- 检查两个部门是否存在
    PERFORM check_department_exists(v_department);
    PERFORM check_department_exists(v_parent);

    -- 检查并获取父类部门的path
    v_parent_path := check_and_get_department_path(v_parent);

    -- 检查节点的标题是否和父类同层次的部门有重名的
    SELECT title INTO v_title FROM department WHERE department_id = v_department;
    PERFORM check_department_title_unique(v_department, v_parent_path, v_title);

    RETURN move_department(v_department, v_parent_path);
END;
$$
LANGUAGE PLPGSQL;


-- 删除部门
CREATE OR REPLACE FUNCTION remove_department(v_department BIGINT)
    RETURNS INT AS
$$
DECLARE
    error_text TEXT;
BEGIN
    PERFORM check_department_exists(v_department);

    -- 根节点不能删除
    IF v_department = 0
    THEN
        RAISE EXCEPTION '400: 禁止删除顶层部门';
    END IF;

    -- 禁止删除有子节点的部门。这里有取巧的部分，找到一个就行了
    PERFORM 1 FROM department WHERE ltree2text(subpath(path, -2, -1)) = v_department :: TEXT;
    IF found THEN
        RAISE EXCEPTION '400: 禁止删除还有子部门存在的部门';
    END IF;

    DELETE FROM department WHERE department_id = v_department;
    RETURN 0;

EXCEPTION
    WHEN OTHERS
        THEN
            GET STACKED DIAGNOSTICS error_text := MESSAGE_TEXT;
            IF error_text ~ 'department_id_fkey'
            THEN
                RAISE EXCEPTION '400: 部门正在被使用，不可删除';
            ELSE
                RAISE;
            END IF;
END;
$$
LANGUAGE PLPGSQL;


-------------------------------------------------- Test data operate -------------------------------------------------
insert into department (department_id, title,path) values(0,'顶层部门','0');
SELECT create_department('{"title":"一级部门1","parent_id":0}');
SELECT remove_department(1);

SELECT create_department('{"title":"一级部门2","parent_id":0}');
SELECT update_department(2,'{"title":"修改的一级部门2","parent_id":0}');

SELECT create_department('{"title":"一级部门3","parent_id":0}');
SELECT create_department('{"title":"二级部门4","parent_id":2}');
SELECT move_department(4,'0.3');
SELECT move_department_to_parent(4,2);

SELECT create_department('{"title":"一级部门5","parent_id":0}');

-- 异常检查
SELECT move_department(4,'0.2.4');
SELECT remove_department(0);
SELECT remove_department(2);

SELECT create_department('{"title":"一级部门5","parent_id":0}');
SELECT create_department('{"title":"一级部门6","parent_id":-1}');