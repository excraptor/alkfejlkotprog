<html>
    <head>
        <jsp:include page="../WEB-INF/Common/header.jsp"></jsp:include>
        <title>Login</title>
    </head>
    <body>
    <jsp:include page="../WEB-INF/Common/menu.jsp"/>
    <div style="display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  min-height: 100vh;">
        <form action="../LoginServlet" method="POST">
            <div class="form-group">
                <label>Name:</label><input type="text" name="username" class="form-control"></br>
            </div>
            <div class="form-group">
                <label>Password:</label><input type="password" name="userpassword" class="form-control"></br>
            </div>
            <button type="submit" class="btn btn-primary">Log in</button>
        </form>
    </div>
    </body>
</html>