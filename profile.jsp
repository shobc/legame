<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>profile</title>
</head>
<body>
<a href="friendSearch">óFíBåüçı</a>
<h1>profile</h1>
<p>id = ${sessionScope.ub.search_id}</p>
<p>name = ${sessionScope.ub.name}</p>
<p>comment = ${sessionScope.ub.single_word}</p>
<p>picturePath = ${sessionScope.ub.top_picture}</p>
<img src="${sessionScope.ub.top_picture}" height="50%" width="30%" style="border: solid;">
</body>
</html>
