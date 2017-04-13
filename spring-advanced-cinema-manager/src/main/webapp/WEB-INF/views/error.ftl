<html>
<head>
<title>Internal Error</title>
</head>
<body>
<h1>Cinema Manager</h1>
<h2>Internal Error:</h2>
<p>${exception?html?replace('\n', '<br>')}</p>
<h2>Details:</h2>
<p>${details?html?replace('\n', '<br>')}</p>
</body>
</html>