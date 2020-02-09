<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Homework11-12</title>
</head>
<body>
<h2>Homework 11 - 12</h2>
<h3>Users</h3>

<c:choose>
    <c:when test="${!empty users}">
        <table>
            <thead>
            <tr>
                <td>User name</td>
                <td>password</td>
                <td>Create date</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.userName}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.createdBy}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        Users are not found
    </c:otherwise>
</c:choose>


</form>
</body>
</html>
