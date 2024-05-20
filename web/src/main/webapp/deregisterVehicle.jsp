<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Deregister Vehicle</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Deregister Vehicle</h1>
        <nav>
          <ul>
            <c:if test="${sessionScope.user != null}">
              <li><a href="index.jsp">Home</a></li>
              <li><a href="manageVehicles.jsp">Manage Vehicles</a></li>
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
          <h2>Are you sure you want to deregister this vehicle?</h2>
          <div class="vehicle-details">
            <p>ID: ${vehicle.id}</p>
            <p>Type: ${vehicle.type}</p>
            <p>License Plate: ${vehicle.licensePlate}</p>
            <p>Capacity: ${vehicle.capacity}</p>
          </div>
          <form id="deregisterVehicleForm">
            <input
              type="hidden"
              id="vehicleId"
              name="vehicleId"
              value="${vehicle.id}"
            />
            <button type="submit" class="btn btn-danger">
              Deregister Vehicle
            </button>
            <a href="manageVehicles.jsp" class="btn">Cancel</a>
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
