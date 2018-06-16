<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${orderList.size() == 0}">
    <div class="container" style="margin-top: 10%">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h3>There are no Orders yet</h3>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${orderList.size() != 0}">

    <h3>The List of Books</h3>
    <table class="table table-hover">
        <thead>
        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Address</td>
            <td>Book Name</td>
            <td>Quantity</td>
            <td>Order Date</td>
            <td>Details</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.getFirstName()}</td>
                <td>${order.getLastName()}</td>
                <td>${order.getAddress()}</td>
                <td>${order.getBookName()}</td>
                <td>${order.getQuantity()}</td>
                <td>${order.getOrderDate()}</td>
                <td><a href="/order/${order.getId()}">Details</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/">Main page</a>
</c:if>
