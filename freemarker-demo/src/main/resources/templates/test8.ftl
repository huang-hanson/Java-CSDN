<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
学生个数：${stus?size}
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>出生日期date</td>
        <td>出生日期time</td>
        <td>出生日期datetime</td>
        <td>出生日期(自定义)</td>

    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td>   <#--说明： _index：得到循环的下标，使用方法是在stu后边加"_index"，它的值是从0开始-->
            <td <#if stu.name =='赵子龙'>style="background:red;"</#if>>${stu.name}</td>
            <td <#if stu.age gt 20>style="background:yellow;"</#if>>${stu.age}</td>
            <td <#if (stu.wallet > 999)>style="background:blue;"</#if>>${stu.wallet}</td>
            <td>${stu.birthday?date}</td>
            <td>${stu.birthday?time}</td>
            <td>${stu.birthday?datetime}</td>
            <td>${stu.birthday?string("yyyy年MM月")}</td>
        </tr>
    </#list>
</table>
</body>
</html>
