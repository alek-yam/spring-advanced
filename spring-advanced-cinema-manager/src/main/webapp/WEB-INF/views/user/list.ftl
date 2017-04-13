<html>
<head>
<title>Users</title>
</head>
<body>
<h1>Cinema Manager</h1>
<h2>Users:</h2>
<table cellpadding="7" border="1">
    <tr>
		<th>ID</th>
        <th>Name</th>
        <th>Birthday</th>
        <th>Email</th>
    </tr>
    <#list users as user>
    <tr>
    	<td>${user.id}</td>
        <td>${user.fullName}</td>
        <td>${user.birthday?string["dd.MM.yyyy"]}</td>
        <td>${user.email}</td>
    </tr>
    </#list>
  </table>
</body>
</html>