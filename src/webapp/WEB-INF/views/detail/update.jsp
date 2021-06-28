<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/update.js"></script>
</head>
<body>
<h2>수정 페이지</h2>
<sf:form action="" method="post" modelAttribute="postDto">
    <label for="inputTitle">제목 : </label>
    <sf:input path="title" id="inputTitle" type="text" style="width:420px"/><br>
    <div id="errorTitle" style="color:red"></div>
    <br>
    <label for="inputContent">내용 : </label>
    <sf:textarea path="content" id="inputContent" type="text" rows="4" cols="50"/><br>
    <div id="errorContent" style="color:red"></div>
    <br>
    <button type="submit" id="updateButton">수정</button>
    <button type="button" value="${postId}" id="cancelButton">취소</button>
</sf:form>
</body>
</html>
