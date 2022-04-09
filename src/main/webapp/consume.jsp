<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/consumer"/>">
    <input type="submit" value="Receive message">
</form>

<c:if test="${not empty res}">
    Your message: ${res}
</c:if>

<c:if test="${not empty error}">
    <span style="color: red">${error}</span>
</c:if>

</body>
</html>
