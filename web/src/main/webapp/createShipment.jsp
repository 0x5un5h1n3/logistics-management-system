<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Shipment</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
</head>
<body>
<header>
    <div class="container">
        <h1>Create Shipment</h1>
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
            <form id="createShipmentForm" action="<c:url value="/createShipment"/>" method="post">
                <div class="form-group">
                    <label for="origin">Origin:</label>
                    <input type="text" id="origin" name="origin" required/>
                </div>
                <div class="form-group">
                    <label for="destination">Destination:</label>
                    <input type="text" id="destination" name="destination" required/>
                </div>
                <div class="form-group">
                    <label for="shippingDate">Shipping Date:</label>
                    <input type="date" id="shippingDate" name="shippingDate" required/>
                </div>
                <button type="submit" class="btn">Create Shipment</button>
            </form>
            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>
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
