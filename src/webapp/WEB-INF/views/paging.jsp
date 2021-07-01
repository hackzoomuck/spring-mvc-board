<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<jsp:useBean id="search" scope="request" type="board.dto.SearchDto"/>
<c:if test="${search.startPageNumber ne 1 && search.endPageNumber eq search.totalEndPageNumber}">
    <a href="search?postItem=${search.postItem}&postItemValue=${search.postItemValue}&pageNumber=1">맨앞으로&nbsp</a>
</c:if>
<c:if test="${search.startPageNumber ne 1}">
    <a href="search?postItem=${search.postItem}&postItemValue=${search.postItemValue}&pageNumber=${search.startPageNumber-1}">이전&nbsp&nbsp</a>
</c:if>

<c:forEach var="page" begin="${search.startPageNumber}" end="${search.endPageNumber}">

    <a href="${pageContext.request.contextPath}/search?postItem=${search.postItem}&postItemValue=${search.postItemValue}&pageNumber=${page}"
            <c:if test="${search.pageNumber eq page}"> style="color:red"</c:if>>
        <c:out value="${page}"/>
    </a>
    &nbsp
</c:forEach>

<c:if test="${search.endPageNumber ne search.totalEndPageNumber}">
    <a href="search?postItem=${search.postItem}&postItemValue=${search.postItemValue}&pageNumber=${search.endPageNumber+1}">다음&nbsp&nbsp</a>
</c:if>
<c:if test="${search.startPageNumber eq 1 && search.endPageNumber ne search.totalEndPageNumber}">
    <a href="search?postItem=${search.postItem}&postItemValue=${search.postItemValue}&pageNumber=${search.totalEndPageNumber}">맨끝으로&nbsp&nbsp</a>
</c:if>

