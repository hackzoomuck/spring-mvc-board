<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<h2>상세 페이지</h2>
    <div>제목 : <c:out value="${postDto.title}" /></div><hr>
    <div style="padding-bottom: 20px">내용 : <c:out value="${postDto.content}"/></div>
    <form:form action="update/${postId}" method="get">
        <button type="button" id="getListButton">목록</button>
        <button type="submit" id="updateButton">수정</button>
        <button type="button" id="deleteButton" value="${postId}">삭제</button>
    </form:form>
</body>
<script>
  $(document).ready(function (){
    $("#getListButton").on("click", function (){
      const url = window.location.origin + "/search";
      window.location.assign(url);
    })
    $("#deleteButton").on("click", function (){
      if(window.confirm("삭제하시겠습니까?")) {
        const url = window.location.origin + "/detail/delete/" + $(this).val();
        window.location.assign(url);
      }
    })
  })
</script>
</html>
