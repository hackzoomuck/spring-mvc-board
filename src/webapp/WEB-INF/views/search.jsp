<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en" xml:lang="en">
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/search.js"></script>
    <link rel="stylesheet" href="/css/search.css">
</head>
<body>
<h3>게시물 찾기</h3>
<form action="search" method="get" id="searchForm">
    <label for="postItem"> </label>
    <select name="postItem" id="postItem">
        <option value="postAll" <c:if test="${param.postItem == 'postAll'}">selected</c:if>>전체
        </option>
        <option value="title" <c:if test="${param.postItem == 'title'}">selected</c:if>>제목</option>
        <option value="content" <c:if test="${param.postItem == 'content'}">selected</c:if>>내용
        </option>
    </select>
    <label for="inputPostItemValue"> </label>
    <input type="text" id="inputPostItemValue" name="postItemValue" value="${param.postItemValue}"/>
    <button type="submit">검색</button>
    <input type="button" value="전체검색" id="btnFindAll">
</form>

<h3>게시물 :</h3>
<table class="borderBox" aria-describedby="table for post">
    <colgroup>
        <col style="width:50px; "/>
        <col style="width:300px;"/>
        <col style="width:500px;"/>
    </colgroup>
    <tr>
        <th id="thNumber" style="text-align: center;">번호</th>
        <th id="thTitle" style="text-align: center;">제목</th>
        <th id="thContent" style="text-align: center;">내용</th>
    </tr>
    <c:if test="${search.postDtoList.size()==0}">
        <tbody>
        <td colspan="3" style="text-align: center;">검색된 내용이 없습니다.</td>
        </tbody>
    </c:if>
    <tbody id="postTbodyId">
    <c:forEach var="post" items="${search.postDtoList}">
        <tr style="cursor: pointer;">
            <td style="text-align: center"><c:out value="${post.postId}"/></td>
            <td><c:out value="${post.title}"/></td>
            <td><c:out value="${post.content}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<button><a href="${pageContext.request.contextPath}/register">등록</a></button>
<%--TODO
1. 페이지 번호 출력 : 처음 페이지는 1로 searchDto의 pageNumber = 1로 초기화 (1번이 선택되어 있어야 한다.)
1) pageCount = 5 로 번호만 출력.(listCount는 3개로 한다.) :
2) 각 번호를 누를 수 있게 해준다.(데이터를 전달. 해당 데이터는 하이라이트).
--%>
<div>
    <c:forEach var="page" begin="${search.startPageNumber}" end="${search.endPageNumber}">
        <button><c:out value="${page}"/></button>
    </c:forEach>
</div>
</body>
</html>
