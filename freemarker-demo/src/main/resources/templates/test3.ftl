<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
遍历数据模型中的stuMap(map数据)，第一种方法：在中括号中填写map的key，第二种方法：在map后边直接加"点(.) key"
<br/>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
输出stu1的学生信息：<br/>
姓名：${stu1.name}<br/>
年龄：${stu1.age}<br/>
遍历map中的key.stuMap?keys就是列表(是一个list)<br/>
遍历输出两个学生信息：<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stuMap?keys as k>
        <tr>
            <td>${k_index + 1}</td>
            <td>${stuMap[k].name}</td>
            <td>${stuMap[k].age}</td>
            <td>${stuMap[k].wallet}</td>
        </tr>
    </#list>
</table>

</body>
</html>
