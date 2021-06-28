<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/searchJS.js"></script>
    <style>
        #postTbodyId > tr:hover {
          background-color : #ff0;
        }
        .borderBox {
          border-style: solid;
        }
    </style>
</head>
<body>
<h3>게시물 찾기</h3>
<form action="search" method="get" id="searchForm">
    <select name="postItem" id="postItem">
        <option value="postAll" <c:if test="${param.postItem == 'postAll'}">selected</c:if>>전체</option>
        <option value="title" <c:if test="${param.postItem == 'title'}">selected</c:if>>제목</option>
        <option value="content" <c:if test="${param.postItem == 'content'}">selected</c:if>>내용</option>
    </select>
    <input type="text" id="inputPostItemValue" name="postItemValue" value="${param.postItemValue}"/>
    <button type="submit">검색</button>
    <input type="button" value="전체검색" id="btnFindAll">
</form>

<h3>게시물 :</h3>
<table class="borderBox">
    <colgroup>
        <col style="width:50px; "/>
        <col style="width:300px;"/>
        <col style="width:500px;"/>
    </colgroup>
    <thead>
        <tr>
            <td style="text-align: center;">번호</td>
            <td style="text-align: center;">제목</td>
            <td style="text-align: center;">내용</td>
        </tr>
    </thead>
    <c:if test="${postList.size()==0}">
        <tbody>
            <td colspan="3" style="text-align: center;">검색된 내용이 없습니다.</td>
        </tbody>
    </c:if>
    <tbody id="postTbodyId">
    <c:forEach var="post" items="${postList}">
        <tr style="cursor: pointer;">
            <td style="text-align: center"><c:out value="${post.postId}"/></td>
            <td><c:out value="${post.title}"/></td>
            <td><c:out value="${post.content}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br><button><a href="${pageContext.request.contextPath}/register">등록</a></button>
</body>
</html>
