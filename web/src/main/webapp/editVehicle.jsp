<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Edit Vehicle</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Edit Vehicle</h1>
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
          <form id="editVehicleForm" action="<c:url value="/editVehicle"/>" method="post">
            <input
              type="hidden"
              id="vehicleId"
              name="vehicleId"
              value="${vehicle.id}"
            />
            <div class="form-group">
              <label for="type">Type:</label>
              <input
                type="text"
                id="type"
                name="type"
                value="${vehicle.type}"
                required
              />
            </div>
            <div class="form-group">
              <label for="licensePlate">License Plate:</label>
              <input
                type="text"
                id="licensePlate"
                name="licensePlate"
                value="${vehicle.licensePlate}"
                required
              />
            </div>
            <div class="form-group">
              <label for="capacity">Capacity:</label>
              <input
                type="number"
                id="capacity"
                name="capacity"
                value="${vehicle.capacity}"
                step="0.01"
                required
              />
            </div>
            <button type="submit" class="btn">Update Vehicle</button>
          </form>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
