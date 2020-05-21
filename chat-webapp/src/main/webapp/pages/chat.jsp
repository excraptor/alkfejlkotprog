<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <jsp:include page="../WEB-INF/Common/header.jsp"/>
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
    <script>tinymce.init({
        selector:'textarea',
        plugins:'emoticons',
        toolbar:'emoticons'
    });</script>
</head>
<body>
<jsp:include page="/ChatController"/>
<jsp:include page="../WEB-INF/Common/menu.jsp"/>
<script>
    let src = "";
    let newImage = document.createElement('img');
</script>
<div>
    <table class="table table-borderless">
        <thead>
        <tr>
            <th scope="col">Messages</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="message" varStatus="loop" items="${requestScope.messages}">
            <tr>
                <td>${message.userNick}:</td>
                <td id="message${loop.index}"></td>
                <c:if test="${message.isImage() == 1}">
                    <script>
                        src = "data:image/jpeg;base64,";
                        src += "${message.message}";
                        newImage.src = src;
                        document.querySelector("#message"+"${loop.index}").innerHTML = newImage.outerHTML;
                    </script>
                </c:if>
                <c:if test="${message.isImage() == 0}">
                    <script>
                        document.querySelector("#message"+"${loop.index}").innerHTML = "${message.message}";
                    </script>
                </c:if>
            </tr>
        </c:forEach>
        <tr>
            <td>Send message:</td>
            <td>
                <form action="../ChatController" method="post">
                    <div class="form-group">
                        <textarea name="messageSent" id="textarea" class="form-control"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Send</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
    <a href="home.jsp">Back to the groups</a>
</div>
</body>
</html>
