<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Welcome</title>
        <jsp:include page="../WEB-INF/Common/header.jsp"/>
        <title>Home</title>
    </head>
    <body>
    <jsp:include page="../WEB-INF/Common/menu.jsp"/>
    <jsp:include page="/HomeController"/>
    <div class="jumbotron" style="position: relative">
        <h1 class="display-4">Welcome</h1>
        <p class="lead">Welcome to Better than Coospace</p>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm">
                <h3>Available chatgroups</h3>
                <div>
                    <c:forEach var="group" items="${requestScope.groupList}">
                        <div class="card" style="width: 18rem">
                            <div class="card-body">
                                <form method="post" action="../ChatServlet">
                                    <input type="hidden" name="groupname" value="${group.name}">
                                    <input class="btn btn-primary" type="submit" value="${group.name}">
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-sm">
                <h4>Add a new chatgroup</h4>
                <form action="../HomeController" method="post">
                    <div class="form-group">
                        <label>Name:</label><input type="text" name="name" class="form-control"></br>
                    </div>
                    <div class="form-group">
                        <label>Category:</label><input type="text" name="category" class="form-control"></br>
                    </div>
                    <button type="submit" class="btn btn-primary">Add new group</button>
                </form>
            </div>
        </div>
    </div>

    </body>
</html>