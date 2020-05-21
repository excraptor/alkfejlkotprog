<html>
<head>
    <jsp:include page="../WEB-INF/Common/header.jsp"></jsp:include>
    <title>Register</title>
</head>
    <body>
    <jsp:include page="../WEB-INF/Common/menu.jsp"/>
    <div style="display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  min-height: 100vh;">
        <form action="../RegisterServlet" method="POST">
            <div class="form-group">
                <label>Name:</label><input type="text" name="username" class="form-control" maxlength="12" required autofocus></br>
            </div>
            <div class="form-group">
                <label>Password:</label><input type="password" name="userpassword" class="form-control" pattern=".{4,}"  title="4 or more characters" required></br>
            </div>
            <div class="form-group">
                <label>Gender:</label><input type="text" name="gender" class="form-control" required></br>
            </div>
            <div class="form-group">
                <label>Age:</label><input type="number" name="age" class="form-control" min="0" max="120" required></br>
            </div>
            <div class="form-group">
                <label>Interest 1:</label><input type="text" name="interest1" class="form-control" required></br>
            </div>
            <div class="form-group">
                <label>Interest 2:</label><input type="text" name="interest2" class="form-control" required></br>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    </body>
</html>