<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<html>
<head>
    <title></title>
</head>
<body>
<h1>“ü‹à</h1>
<p>${money}</p>
<form method="post" action="InputMoneyServlet">
    <input type="hidden" name="money" value="${money}"><br>
    <input type="hidden" name="RandomString" value="${RandomString}"><br>
    <input type="submit" value="Šm’è">
</form>
</body>
</html>