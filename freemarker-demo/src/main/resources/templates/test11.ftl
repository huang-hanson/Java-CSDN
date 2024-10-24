<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
<#--方式1-->
订单id：${order.orderId} <br>
订单名称：${order.orderName} <br>
订单价格：${order.price.price} <br>
订单日期：${order.price.date?string("yyyy年MM月")} <br>

<br>
<br>
<br>


<#--方式2-->
订单id：${order.getOrderId()} <br>
订单名称：${order.getOrderName()} <br>
订单价格：${order.getPrice().getPrice()} <br>
订单日期：${order.getPrice().getDate()?string("yyyy年MM月")} <br>
</body>
</html>
