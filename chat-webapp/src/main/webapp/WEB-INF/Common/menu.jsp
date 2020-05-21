<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <c:if test="${sessionScope.currentUser.nick == null}">
        <a class="navbar-brand" href="#">Home</a>
    </c:if>
    <c:if test="${sessionScope.currentUser.nick != null}">
        <a class="navbar-brand" href="home.jsp">Home</a>
    </c:if>
    <button class="navbar-toggler" type="button" data-toggler="collapse"
            data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarDropDown">
        <ul class="navbar-nav w-100 justify-content-end">
            <c:if test="${sessionScope.currentUser.nick == null}">
                <li class="nav-item"><a href="register.jsp" class="nav-link">Register</a></li>
                <li class="nav-item"><a href="login.jsp" class="nav-link">Login</a></li>
            </c:if>
            <c:if test="${sessionScope.currentUser.nick != null}">
                <li class="nav-item">
                    <form action="../SearchController" method="post" id="searchform" class="form-inline my-2 my-lg-0">
                        <input type="search" name="search" class="form-control" placeholder="Search">
                        <button class="btn my-2 my-sm-0 nav-link" type="submit">Search</button>
                    </form>
                </li>
                <li class="nav-item">
                    <select id="categories" name="categories" form="searchform" class="custom-select custom-select-lg my-2 my-lg-0 form-control">
                        <option value="groupname">Group name</option>
                        <option value="groupcategory">Group category</option>
                        <option value="username">Username</option>
                        <option value="userinterests">User interest</option>
                    </select>
                </li>
                <li class="nav-item" style="padding: 5px">
                    <form action="../LogoutController" method="get" class="form-inline">
                        <button type="submit" class="btn btn-outline-primary">Log out</button>
                    </form>
                </li>
            </c:if>
        </ul>
    </div>
</nav>