-- 这是单行注释

--[[
这是多行注释
 ]]

-- 变量的使用
print "hello";
a=5;
print(a);



--字符串的最新表达，以及替换
arr=[[hello,
world,
this is a beautiful]]
print(arr);
print(string.gsub("aaaabcd","a","z",2));

--数字字符串的算术运算
c="2";
print(c+4);

--计算字符串长度
len="hello";
print(#len);
print(len.."world")

--创建table类型数据,下标从1开始
--table数据类型的使用和for循环
--table可以排序
tab1 = { key1 = "val1", key2 = "val2", "val3" }
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end
local tbl1={};
local tbl2={"apple", "pear", "orange", "grape"};
print(tbl1);
print(tbl2[1]);

table.sort(tbl2);
for k,v in ipairs(tbl2) do
    print(k,v)
end

--函数的使用, 函数可以作为参数赋值和传递
function factorial1(n)
    if n == 0 then
        return 1
    else
        return n * factorial1(n - 1)
    end
end;
print(factorial1(3));

--函数作为参数传递
function testFun(method)
    print(method(3));
end
testFun(factorial1);

--if语句的使用
if type(nil)=="nil" then
    print("相等");
end

d=5;
if(d<1) then
    print(1);
elseif(d<3) then
    print(2);
else
    print(3);
end

--函数的多个返回值
function f()
    return 1,2;
end
a1,a2=f();
print(a1,a2)

