-- 验证连表查询，一张表当两张表使用

--------------------------------------------------- Table Definition ---------------------------------------------------
CREATE TABLE class
(
    class_id     BIGSERIAL,
    teacher_id   BIGINT   NOT NULL,
    student_id   BIGINT   NOT NULL,
    class_name   TEXT,
    PRIMARY KEY (class_id)
);

CREATE TABLE person
(
    person_id   BIGSERIAL,
    name        TEXT,
    PRIMARY KEY (person_id)
);

--------------------------------------------------- AutoCreateClassAnnotationTemplatesWhenCreateClass data operate ---------------------------------------------------
insert into person (person_id, name) values (1,'小红');
insert into person (person_id, name) values (2,'小绿');
insert into person (person_id, name) values (3,'小蓝');

insert into class (class_id, teacher_id, student_id, class_name) values (1,1,2,'1班');
insert into class (class_id, teacher_id, student_id, class_name) values (2,2,3,'2班');
insert into class (class_id, teacher_id, student_id, class_name) values (3,3,1,'3班');

-- 期望结果：1班，小红，小绿
SELECT json_build_object(
          'class_name',   c.class_name,
          'teacher_id',   p1.name,
          'student_id',   p2.name
        )
        FROM class c
        JOIN person p1 ON c.teacher_id =p1.person_id
        JOIN person p2 ON c.student_id =p2.person_id
        where c.class_id=1;
