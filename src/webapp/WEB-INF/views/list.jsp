<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="post" items="${postDtoList}">
    <li><c:out value="${post.title}" /></li>
</c:forEach>
</body>
</html>
