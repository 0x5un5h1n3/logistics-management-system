<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Manage Vehicles</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Manage Vehicles</h1>
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="registerVehicle.jsp">Register New Vehicle</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="container">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Type</th>
              <th>License Plate</th>
              <th>Capacity</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="vehicle" items="${vehicles}">
              <tr>
                <td>${vehicle.id}</td>
                <td>${vehicle.type}</td>
                <td>${vehicle.licensePlate}</td>
                <td>${vehicle.capacity}</td>
                <td>
                  <a href="editVehicle?id=${vehicle.id}">Edit</a>
                  <a href="deregisterVehicle?id=${vehicle.id}">Deregister</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <a href="registerVehicle.jsp" class="btn">Register New Vehicle</a>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
