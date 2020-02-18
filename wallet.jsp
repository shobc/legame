<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>wallet page</title>
</head>
<body>
<a href="HomePageServlet">プロフィールに戻る</a>
<form method="post" action="QRDepositMoneyServlet">
    金額を入力<input type="number" name="money" min="100" step="10"><br>
    <input type="submit" value="確定">
</form>
<p>残高${bb.moneyTotal}</p>
<p>ポイント${bb.pointTotal}</p>
<style>
    td{
        font-size: 12px;
    }
    ul {
        list-style: none;
    }
    li {
        display: inline;
        /*margin-left: 10px;*/
    }
    .pagination {
        text-align: center;
        display: inline-block;
    }

    .pagination a {
        color: black;
        float: left;
        padding: 2px 8px;
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
<%--<display:table name="${sessionScope.propertylist}" export="true" pagesize="10" >--%>
<display:table name="${sessionScope.propertylist}" pagesize="10" >
    <display:column property="money" title="お金" />
    <display:column property="point" title="ポイント"  />
    <display:column property="history" title="履歴"  />
    <display:column property="historydate" title="時間" />
</display:table>
</center>
<p><a href="QRCodeServlet">QRコードを表示</a></p>
</body>
</html>