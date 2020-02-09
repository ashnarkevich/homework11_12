<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title>Homework11-12</title>
</head>
<body>
<h2>Homework 11 - 12</h2>
<h3>Roles</h3>

<c:choose>
    <c:when test="${!empty roles}">
        <table>
            <thead>
            <tr>
                <td>Role name</td>
                <td>Description</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="role" items="${roles}">
                <tr>
                    <td><c:out value="${role.name}"/></td>
                    <td><c:out value="${role.description}"/></td>
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
