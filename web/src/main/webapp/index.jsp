<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Logistics Management System</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Logistics Management System</h1>
        <nav>
          <ul>
            <c:if test="${sessionScope.user != null}">
              <li>Welcome, ${sessionScope.user.username}</li>
              <li><a href="account.jsp">Account</a></li>
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
          <!-- Content for authenticated users -->
          <h2>Welcome to the Logistics Management System</h2>
          <p>You can manage your shipments, cargo, and vehicles here.</p>
          <ul>
            <li><a href="trackShipment.jsp">Track Shipment</a></li>
            <li><a href="manageShipment.jsp">Manage Shipments</a></li>
            <li><a href="manageCargo.jsp">Manage Cargo</a></li>
            <li><a href="manageVehicles.jsp">Manage Vehicles</a></li>
          </ul>
        </c:if>
        <c:if test="${sessionScope.user == null}">
          <p>
            Please login or register to access the Logistics Management System.
          </p>
        </c:if>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
  </body>
</html>
