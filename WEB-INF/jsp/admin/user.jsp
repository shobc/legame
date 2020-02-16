<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/admin/layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/admin/user.css' />">
    </c:param>
    <c:param name="title" value="ìoò^ÉÜÅ[ÉUÅ["/>
    <c:param name="caption" value="ìoò^ÉÜÅ[ÉUÅ["/>
    <c:param name="content">
        <div class="user_search">
            <form id="form" action="SearchUserServlet">
                <input id="box" name="search_id" class="form-control" type="text" placeholder="search_id" />
                <input id="btn" type="submit" value="åüçı" />
            </form>
        </div>
        <div class="containers">
            <div class="user_list">
                <c:forEach var="ul" items="${userList}">
                    <div class="container">
                        <div class="user">
                            <img class="top_picture" src="data:image;base64,${ul.top_picture}" height="10%">
                            <span>${ul.search_id}</span>
                        </div>
                        <div class="item">
                            <c:choose>
                                <c:when test = "${ul.stop_flag=='0'}">
                                    <a href="ReleaseUserServlet?user_id=${ul.user_id}">âèú</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="StopUserServlet?user_id=${ul.user_id}">í‚é~</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <c:if test="${empty userList}">
            <h2 id="noContent">ìoò^Ç≥ÇÍÇƒÇ¢Ç‹ÇπÇÒ</h2>
        </c:if>
    </c:param>
</c:import>