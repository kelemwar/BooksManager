
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-lg-12 text-center" style="margin-top: 2%">
    <h1>Create Book</h1>
</div>
<div align="left" class="col-lg-12">

    <form:form action="/create-book" method="post" >

                <div class="text-center form-group">
                    <legend>Name:</legend>
                    <input name="name" required type="text" class="form-control text-center" value="">
                </div>

                <div >
                    <legend>Description:</legend>
                    <input name="descr" type="text" class="form-control text-center" value="">
                </div>

                <div >
                    <legend>Price:</legend>
                    <input name="price" required type="number" step="0.01" min="0.01" max="10000.0" class="form-control text-center" value="0.01">
                    <%--<form:input path="price" type="number" step="0.01" class="form-control text-center"></form:input>--%>
                </div>

                <div class="text-center form-group">
                <legend>Authors:</legend>

                    <select name="selectedAuthors" required multiple="multiple" size="${authorsList.size()}">
                        <c:forEach items="${authorsList}" var="author">
                            <option value="${author.getId()}">${author.getName()}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="text-center form-group">
                    <legend>Genres:</legend>
                    <select name="selectedGenres" required multiple="multiple" size="${genresList.size()}">
                    <c:forEach items="${genresList}" var="genre">
                        <option value="${genre.getId()}">${genre.getName()}</option>
                    </c:forEach>
                    </select>
                </div>

                <div class="text-center">
                    <input type="submit" value="Create" class="btn btn-success" >
                </div>
</form:form>

</div>
</body>
</html>
