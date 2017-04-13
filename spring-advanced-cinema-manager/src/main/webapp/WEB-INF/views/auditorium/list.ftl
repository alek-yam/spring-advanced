<html>
<head>
<title>Auditoriums</title>
</head>
<body>
<h1>Cinema Manager</h1>
<h2>Auditoriums:</h2>
<table cellpadding="7" border="1">
    <tr>
        <th>Auditorium</th>
        <th>Seats</th>
    </tr>
    <#list auditoriums as audit>
    <tr>
        <td>${audit.name}</td>
        <td align="center">${audit.seats?size}</td>
    </tr>
    </#list>
  </table>
</body>
</html>