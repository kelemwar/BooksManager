<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${bookList.size() == 0}">
    <div class="container" style="margin-top: 10%">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h3>Please add books to the database</h3>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${bookList.size() != 0}">

    <h3>The List of Books</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>Name</td>
            <td>Price</td>
            <td>Details</td>
            <security:authorize access="hasRole('ADMIN')">
            <td>Remove</td>
            </security:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.getName()}</td>
                <td>${book.getPrice()}</td>
                <td><a href="/book/${book.getId()}">Details</a></td>
                <security:authorize access="hasRole('ADMIN')">
                <td><a href="/delete-book/${book.getId()}">Remove</a></td>
                </security:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/">Back To Main</a>
</c:if>