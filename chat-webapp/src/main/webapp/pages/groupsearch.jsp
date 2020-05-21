<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <jsp:include page="../WEB-INF/Common/header.jsp"/>
    <title>Search in groups</title>
</head>
<body>
<jsp:include page="/SearchManager"/>
<jsp:include page="../WEB-INF/Common/menu.jsp"/>
<div class="container">
    <c:forEach var="item" items="${requestScope.searchResult}">
        <div class="row">
            <div class="col-sm">
                Group name: ${item.name}
            </div>
            <div class="col-sm">
                Group theme: ${item.theme}
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
