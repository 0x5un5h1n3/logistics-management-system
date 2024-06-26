<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cancel Shipment</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>
<body>
<header>
    <div class="container">
        <h1>Cancel Shipment</h1>
        <nav>
            <ul>
                <c:if test="${sessionScope.user != null}">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="manageShipment.jsp">Manage Shipments</a></li>
                    <li><a href="logout">Logout</a></li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li><a href="login.jsp">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
</header>
<main>
    <div class="container">
        <c:if test="${sessionScope.user != null}">
            <h2>Are you sure you want to cancel this shipment?</h2>
            <p>ID: ${shipment.id}</p>
            <p>Origin: ${shipment.origin}</p>
            <p>Destination: ${shipment.destination}</p>
            <p>Shipping Date: ${shipment.shippingDate}</p>
              <form action="<c:url value="/cancelShipment"/>" method="post">
                <input type="hidden" name="shipmentId" value="${shipmentId}"/>
                <button type="submit" class="btn btn-danger">Cancel Shipment</button>
                <a href="manageShipment.jsp" class="btn">Cancel</a>
              </form>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <p>Please login or register to access this page.</p>
        </c:if>
    </div>
</main>
<footer>
    <div class="container">&copy; 2024 Logistics Management System</div>
</footer>
<script src="js/script.js"></script>
</body>
</html>
