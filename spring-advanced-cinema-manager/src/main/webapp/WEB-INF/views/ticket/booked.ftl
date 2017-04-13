<html>
<head>
<title>Booked tickets</title>
</head>
<body>
<h1>Cinema Manager</h1>
<h2>Booked tickets:</h2>
<table cellpadding="7" border="1">
    <tr>
        <th>User</th>
        <th>Event</th>
        <th>Date</th>
        <th>Seat</th>
    </tr>
    <#list tickets as ticket>
    <tr>
    	<td>${ticket.userName}</td>
        <td>${ticket.eventName}</td>
        <td>${ticket.date?string["dd.MM.yyyy, HH:mm"]}</td>
        <td align="center">${ticket.seat}</td>
    </tr>
    </#list>
  </table>
</body>
</html>