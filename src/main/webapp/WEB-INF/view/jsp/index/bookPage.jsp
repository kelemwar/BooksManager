<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
</head>
<body>
<div class="row text-center">
    <h1>Book Detail</h1>
</div>
<div>
    <TABLE>
        <TR><TH>Name:</TH>
            <TD>${currBook.getName()}</TD></TR>
        <TR><TH>Description:</TH>
            <TD>${currBook.getDescription()}</TD> </TR>
        <TR><TH>Price:</TH>
            <TD>${currBook.getPrice()}</TD> </TR>
        <TR><TH>Authors:</TH>
            <c:if test="${authorsForBook == null}">
                <TD>There is no Authors yet</TD>
            </c:if>
            <c:if test="${authorsForBook.size() == 0}">
                <TD>There is no Authors yet</TD>
            </c:if>
        <c:if test="${authorsForBook.size() != 0}"><TD>
            <c:forEach var="author" items="${authorsForBook}">
                <li>${author.getName()}</li>
            </c:forEach></TD>
        </c:if>

    </TR>
        <TR><TH>Genres:</TH>
            <c:if test="${genresForBook == null}">
                <TD>There is no Genres yet</TD>
            </c:if>
            <c:if test="${genresForBook.size() == 0}">
                <TD>There is no Genres yet</TD>
            </c:if>
            <c:if test="${genresForBook.size() != 0}"><TD>
                <c:forEach var="genre" items="${genresForBook}">
            <li>     ${genre.getName()} </li>
                </c:forEach></TD>
            </c:if>

        </TR>

        <security:authorize access="hasRole('ADMIN')">
         <TR>
             <td><a href="/edit-book-form/${currBook.getId()}">Edit Book</a></td>
             <td><a href="/delete-book/${currBook.getId()}">Remove</a></td>
         </TR>
    </security:authorize>

    </TABLE>




    <div class="container">
        <div class="row text-center" style="margin-top: 2%">
            <h1>Submit Order</h1>
        </div>
        <div class="row">

                    <form action="/create-order" method="post">
                        <div class="text-center form-group">
                            <input type="hidden" name="bookId" value="${currBook.getId()}">

                            <div class="text-center form-group">
                                <legend>First Name</legend>
                                <input type="text" name="firstName" required value="">
                                <legend>Last Name</legend>
                                <input type="text" name="lastName" required value="">
                                <legend>Address</legend>
                                <input type="text" name="address" required value="">
                                <legend>Quantity</legend>
                                <input type="number" min="1" max="255" required value="1" name="quant">
                            </div>
                            <div class="text-center">
                                <input type="submit" value="Create" class="btn btn-success" style="width: 30%">
                            </div>
                        </div>
                    </form>

        </div>
    </div>


    <a href="/books-list">See All Books</a>
    <a href="/">Back To Main</a>
</div>
</body>
</html>
