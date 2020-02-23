<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/create-timeline.css' />">
        <script  src="<c:url value='/js/create-timeline.js' />"></script>
        <title>ìäçe</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <div>
                <a href="TimeLineServlet"><img class="cross" src="<c:url value='/image/cross.png' />" width="50px"></a>
            </div>
            <div>
                <input type="submit" class="btn" id="create_timeline_btn" form="create_timeline_form" value="ìäçe">
            </div>
        </div>
        <form action="CreateTimeLineServlet" method="post" id="create_timeline_form"  enctype="multipart/form-data">
            <div class="text">
                <textarea class="timeline_sentence" name="timeline_sentence" maxlength="250" placeholder="ç°âΩÇµÇƒÇÈÅH" required></textarea>
                <div class="slide-wrap"></div>
                <div class="image_btn">
                    <label class="upload-img-btn" style="left: 5px;">
                        âÊëúÇë}ì¸Ç∑ÇÈ
                        <input type="file" id="icon" name="icon" multiple="multiple" accept="image/*" style="display:none">
                    </label>
                </div>
            </div>
        </form>
    </c:param>
</c:import>