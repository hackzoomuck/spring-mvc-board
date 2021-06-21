<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>게시물 찾기</h3>
<form action="search" method="get" id="searchForm">
    <select name="postItem" id="postItem">
        <option value="postAll" <c:if test="${param.postItem == 'postAll'}">selected</c:if>>전체</option>
        <option value="postId" <c:if test="${param.postItem == 'postId'}">selected</c:if>>게시물 번호</option>
        <option value="title" <c:if test="${param.postItem == 'title'}">selected</c:if>>제목</option>
        <option value="content" <c:if test="${param.postItem == 'content'}">selected</c:if>>내용</option>
    </select>
    <input type="text" id="inputPostItemValue" name="postItemValue" value="${param.postItemValue}"/>
    <button type="submit">검색</button>
    <input type="button" value="전체검색" id="btnFindAll">
</form>
<h3>게시물 :</h3>
<table>
    <thead>
        <tr>
            <td>번호</td>
            <td>제목</td>
            <td>내용</td>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${postList}">
        <tr>
            <td><c:out value="${post.postId}"/></td>
            <td><c:out value="${post.title}"/></td>
            <td><c:out value="${post.content}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script>
  const btnFindAll = document.getElementById("btnFindAll");
  btnFindAll.addEventListener("click", function (){
    const postItem = document.getElementById("postItem");
    const inputPostItemValue = document.getElementById("inputPostItemValue");
    postItem.options[postItem.selectedIndex].value = "postAll";
    inputPostItemValue.value = "";
    document.forms["searchForm"].submit();
  })
</script>
</html>
