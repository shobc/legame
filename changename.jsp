<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script>
        $(function(){
           var path = '${pageContext.request.servletPath}';
           if(path === '/changename'){

           }else if(){

           }else if(){

           }else if(){

           }
        });
    </script>
    <title>名前を変更</title>
</head>
<body>
<h1>名前</h1>
<table>
    <form action="ChangeNameServlet" method="post">
        <input type="text" name="name" value="${sessionScope.ub.name}" required>
        <input type="submit" value="変更">
    </form>
</table>
</body>
</html>