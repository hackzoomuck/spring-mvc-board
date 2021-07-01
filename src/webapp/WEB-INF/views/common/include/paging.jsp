<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<c:if test="${page.startPageNumber ne 1 && page.endPageNumber eq page.totalEndPageNumber}">
    <a href="${pageContext.request.contextPath}${search.uri}&pageNumber=1">맨앞으로&nbsp</a>
</c:if>
<c:if test="${page.startPageNumber ne 1}">
    <a href="${pageContext.request.contextPath}${search.uri}&pageNumber=${page.startPageNumber-1}">이전&nbsp&nbsp</a>
</c:if>

<c:forEach var="page" begin="${page.startPageNumber}" end="${page.endPageNumber}">
    <a href="${pageContext.request.contextPath}${search.uri}&pageNumber=${page}"
            <c:if test="${search.pageNumber eq page}"> style="color:red"</c:if>>
        <c:out value="${page}"/>
    </a>
    &nbsp
</c:forEach>

<c:if test="${page.endPageNumber ne page.totalEndPageNumber}">
    <a href="${pageContext.request.contextPath}${search.uri}&pageNumber=${page.endPageNumber+1}">다음&nbsp&nbsp</a>
</c:if>
<c:if test="${page.startPageNumber eq 1 && page.endPageNumber ne page.totalEndPageNumber}">
    <a href="${pageContext.request.contextPath}${search.uri}&pageNumber=${page.totalEndPageNumber}">맨끝으로&nbsp&nbsp</a>
</c:if>
