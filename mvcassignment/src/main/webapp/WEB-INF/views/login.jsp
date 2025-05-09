<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>Login</h2>

<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label>Email:</label>
    <input type="email" name="email" required/><br/><br/>
    <label>Password:</label>
    <input type="password" name="password" required/><br/><br/>
    <button type="submit">Login</button>
</form>

</body>
</html>