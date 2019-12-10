<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
    <title>wallet page</title>
</head>
<body>
<form method="post" action="InputMoneyServlet">
    ���z�����<input type="number" name="pay" min="100" step="10"><br>
    <input type="submit" value="�m��">
</form>
<p>�c��${bb.moneyTotal}</p>
<p>�|�C���g${bb.pointTotal}</p>
<style>
    ul {
        list-style: none;
    }
    li {
        display: inline;
        margin-left: 10px;
    }
    .pagination {
        display: inline-block;
    }

    .pagination a {
        color: black;
        float: left;
        padding: 8px 16px;
        text-decoration: none;
        border: 1px solid #ddd;
    }

    .pagination a.active {
        background-color: #4CAF50;
        color: white;
        border: 1px solid #4CAF50;
    }

    .pagination a:hover:not(.active) {background-color: #ddd;}

    .pagination a:first-child {
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
    }

    .pagination a:last-child {
        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
    }
    #main{
        color:white;
        background-color: #ccc;
    }


</style>
<center>
<display:table name="${sessionScope.propertylist}" export="true" pagesize="10" >
    <display:column property="money" title="����" />
    <display:column property="point" title="�|�C���g"  />
    <display:column property="history" title="����"  />
    <display:column property="historydate" title="����"  sortable="true" />
</display:table>
</center>
<p><a href="QRCodeServlet">QR�R�[�h��\��</a></p>
</body>
</html>