<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello,${name}!
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td> <#--说明： _index：得到循环的下标，使用方法是在stu后边加"_index"，它的值是从0开始-->
            <td <#if stu.name =='张翼德'>style="background:red;"</#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.wallet}</td>
        </tr>
    </#list>
</table>
</body>
</html>
