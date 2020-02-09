<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Homework11-12</title>
</head>
<body>
<h2>Homework 11 - 12</h2>

<form action="${pageContext.request.contextPath}/login/enter" class="form" method="post">
    <div class="form__field">
        <input type="text" name="username" placeholder="Name" required maxlength="40"/>
        <span class="form__error">Max size 40</span>
    </div>
    <div class="form__field">
        <input type="password" name="password" placeholder="password" required maxlength="40"/>
        <span class="form__error">Max size 40</span>
    </div>
    <button type="submit">Send</button>
</form>

</form>
</body>
</html>
