<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Manage Shipments</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Manage Shipments</h1>
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="createShipment.jsp">Create New Shipment</a></li>
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
              <th>Origin</th>
              <th>Destination</th>
              <th>Shipping Date</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="shipment" items="${shipments}">
              <tr>
                <td>${shipment.id}</td>
                <td>${shipment.origin}</td>
                <td>${shipment.destination}</td>
                <td>${shipment.shippingDate}</td>
                <td>
                  <a href="editShipment?id=${shipment.id}">Edit</a>
                  <a href="cancelShipment?id=${shipment.id}">Cancel</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <a href="createShipment.jsp" class="btn">Create New Shipment</a>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
