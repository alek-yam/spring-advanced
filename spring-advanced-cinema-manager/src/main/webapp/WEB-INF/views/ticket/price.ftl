<html>
<head>
<title>Price List</title>
</head>
<body>
<h1>Cinema Manager</h1>
<h2>Price List:</h2>
<table cellpadding="7" border="1">
    <tr>
        <th>Event</th>
        <th>Price</th>
        <th>Vip</th>
    </tr>
    <#list priceList as item>
    <tr>
        <td>${item.eventName}</td>
        <td>${item.price}</td>
        <td>${item.vipPrice}</td>
    </tr>
    </#list>
  </table>
</body>
</html>