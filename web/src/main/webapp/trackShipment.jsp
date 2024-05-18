<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Track Shipment</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Track Shipment</h1>
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="manageShipments.jsp">Manage Shipments</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="container">
        <form action="trackShipment" method="post">
          <div class="form-group">
            <label for="shipmentId">Shipment ID:</label>
            <input type="text" id="shipmentId" name="shipmentId" required />
          </div>
          <button type="submit" class="btn">Track</button>
        </form>

        <c:if test="${not empty shipment}">
          <h2>Shipment Details</h2>
          <p>ID: ${shipment.id}</p>
          <p>Origin: ${shipment.origin}</p>
          <p>Destination: ${shipment.destination}</p>
          <p>Shipping Date: ${shipment.shippingDate}</p>
        </c:if>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
