<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/register.js"></script>
</head>
<body>
<h2>등록 페이지</h2>
<sf:form action="register" method="post" modelAttribute="post">
    <label for="inputTitle">제목 : </label>
    <sf:input path="title" id="inputTitle" type="text" style="width:420px"/><br>
    <div id="errorTitle" style="color:red"></div>
    <br>
    <label for="inputContent">내용 : </label>
    <sf:textarea path="content" id="inputContent" rows="4" cols="50"/><br>
    <div id="errorContent" style="color:red"></div>
    <br>
    <button type="submit" id="registerButton">등록</button>
    <button><a href="${pageContext.request.contextPath}/search">취소</a></button>
</sf:form>
</body>
</html>
