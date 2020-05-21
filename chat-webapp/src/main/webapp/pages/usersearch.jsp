<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search in users</title>
    <jsp:include page="../WEB-INF/Common/header.jsp"/>
</head>
<body>
<jsp:include page="/SearchManager"/>
<jsp:include page="../WEB-INF/Common/menu.jsp"/>
<div class="container">
    <c:forEach var="item" items="${requestScope.searchResult}">
        <div class="row">
            <div class="col-sm">
                Username: ${item.nick}
            </div>
            <div class="col-sm">
                Gender: ${item.gender}
            </div>
            <div class="col-sm">
                Age: ${item.age}
            </div>
            <div class="col-sm">
                Interest 1: ${item.interest1}
            </div>
            <div class="col-sm">
                Interest 2: ${item.interest2}
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
