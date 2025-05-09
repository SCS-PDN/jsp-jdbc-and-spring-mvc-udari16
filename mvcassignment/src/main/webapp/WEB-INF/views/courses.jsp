<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<h2>Available Courses</h2>

<c:if test="${not empty error}">
    <div style="color:red;">${error}</div>
</c:if>

<table border="1" cellpadding="10" cellspacing="0">
    <thead>
        <tr>
            <th>Course Name</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="course" items="${courses}">
            <tr>
                <td>${course.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/register/${course.id}" method="post" style="margin:0;">
                        <button type="submit">Register</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
