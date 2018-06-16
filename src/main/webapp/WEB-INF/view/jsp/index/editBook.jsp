<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-lg-12 text-center" style="margin-top: 2%">
    <h1>Edit Book</h1>
</div>
<div align="left" class="col-lg-12">

            <form:form action="/edit-book/${currBook.getId()}" method="post" >

                <div class="text-center form-group">
                    <legend>Name:</legend>
                    <input name="name" type="text" class="form-control text-center" required value="${currBook.getName()}">
                </div>

                <div >
                    <legend>Description:</legend>
                    <input name="descr" type="text" class="form-control text-center" required value="${currBook.getDescription()}">
                </div>

                <div >
                    <legend>Price:</legend>
                    <input name="price" type="number" step="0.01" min="0.01" max="10000.0" class="form-control text-center" required value="${currBook.getPrice()}">
                </div>

                <div class="text-center form-group">
                    <legend>Authors:</legend>

                    <c:if test="${authorsForBook == null}">
                    <select name="selectedAuthors" required multiple="true" size="5">
                        <c:forEach items="${authorList}" var="author">
                        <option value="${author.getId()}">${author.getName()}</option>
                        </c:forEach>
                    </select>
                    </c:if>
                    <c:if test="${authorsForBook.size() == 0}">
                        <select name="selectedAuthors" required multiple="true" size="5">
                            <c:forEach items="${authorList}" var="author">
                                <option value="${author.getId()}">${author.getName()}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${authorsForBook.size() != 0}">
                            <select name="selectedAuthors" required multiple="true" size="5">
                                <c:forEach items="${authorList}" var="author">
                                    <c:set var="isSelectedAuthor" value="false" />
                                    <c:forEach items="${authorsForBook}" var="curr">
                                        <c:if test="${curr.getId()==author.getId()}">
                                            <c:set var="isSelectedAuthor" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${isSelectedAuthor}">
                                            <option value="${author.getId()}" selected="selected">${author.getName()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${author.getId()}">${author.getName()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                    </c:if>
                </div>

                <div class="text-center form-group">
                    <legend>Genres:</legend>
                    <c:if test="${genresForBook == null}">
                    <select name="selectedGenres" required multiple="true" size="5">
                            <c:forEach items="${genreList}" var="genre">
                                <option value="${genre.getId()}">${genre.getName()}</option>
                            </c:forEach>
                    </select>
                    </c:if>
                    <c:if test="${genresForBook.size() == 0}">
                        <select name="selectedGenres" required multiple="true" size="5">
                            <c:forEach items="${genreList}" var="genre">
                                <option value="${genre.getId()}">${genre.getName()}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${genresForBook.size() != 0}">
                            <select name="selectedGenres" required multiple="true" size="5">
                                <c:forEach items="${genreList}" var="genre">
                                    <c:set var="isSelectedGenre" value="false" />
                                    <c:forEach items="${genresForBook}" var="curr">
                                        <c:if test="${curr.getId()==genre.getId()}">
                                            <c:set var="isSelectedGenre" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${isSelectedGenre}">
                                            <option value="${genre.getId()}" selected="selected">${genre.getName()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${genre.getId()}">${genre.getName()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                    </c:if>
                </div>

                <div class="text-center">
                    <input type="submit" value="Edit" class="btn btn-success" style="width: 30%">
                </div>
            </form:form>

</div>
</body>
</html>
