<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${genreList.size() == 0}">
  <div class="container" style="margin-top: 10%">
    <div class="row">
        <h3>Please add genres to the database</h3>
    </div>
  </div>
</c:if>
    <c:if test="${genreList.size() != 0}">
      <table >
        <tbody>
        <tr> <h2>Current Books By Genres: </h2>
        <c:forEach var="genre" items="${genreList}">
          <th><a href="/books-list-by-genre/${genre.getId()}">${genre.getName()}</a></th>
        </c:forEach>
        </tr>
        </tbody>
      </table>
    </c:if>
<c:if test="${authorList.size() == 0}">
  <div class="container" style="margin-top: 10%">
    <div class="row">
      <div class="col-lg-12 text-center">
        <h3>There are no authors yet</h3>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${authorList.size() != 0}">
  <table >
    <tbody>
    <tr> <h2>Current Books By Authors: </h2>
      <c:forEach var="author" items="${authorList}">
        <th><a href="/books-list-by-author/${author.getId()}">${author.getName()}</a></th>
      </c:forEach>
    </tr>
    </tbody>
  </table>
</c:if>
<div class="row">
</div>

<li class="text-center"><a href="/login">Login</a></li>
<security:authorize access="hasRole('ADMIN')">
  <li class="text-center"><a href="/logout">logout</a></li>
  <table >
    <tbody>
    <tr> <td><a href="/create-book-form">Create Book</a> </td></tr>
    <tr> <td><a href="/orders-list">See All Orders</a></td> </tr>
    </tbody>
  </table>

</security:authorize>

<table >
  <tbody>
  <tr> <td><a href="/books-list">See All Books</a> </td></tr>
  </tbody>
</table>



