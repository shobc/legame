<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/admin/layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/admin/approval-shop-list.css' />">
    </c:param>
    <c:param name="title" value="申請許可"/>
    <c:param name="caption" value="申請許可"/>
    <c:param name="content">
        <c:forEach var="sl" items="${shopList}">
            <div class="approval_user">
                <div class="approval_info">
                    <div>
                        <img class="approval_image" src="data:image;base64,${sl.picture}" width="250px">
                    </div>
                    <div>
                        <p>${sl.user_name}</p>
                        <p>${sl.mail}</p>
                    </div>
                    <div class="delete_approval">
                        <div >
                            <span><a href="DoApprovalServlet?shop_admin_user_id=${sl.shop_admin_user_id}&mail=${sl.mail}">許可</a></span>
                        </div>
                        <div>
                            <span><a href="DeleteAdminUserAccountServlet?shop_admin_user_id=${sl.shop_admin_user_id}&mail=${sl.mail}">削除</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:if test="${empty shopList}">
            <h2 id="noContent">登録されていません</h2>
        </c:if>
    </c:param>
</c:import>