<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Cancel Shipment</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <main>
      <div class="container">
        <h1>Cancel Shipment</h1>
        <p>Are you sure you want to cancel the following shipment?</p>
        <p>ID: ${shipment.id}</p>
        <p>Origin: ${shipment.origin}</p>
        <p>Destination: ${shipment.destination}</p>
        <p>Shipping Date: ${shipment.shippingDate}</p>
        <form action="cancelShipment" method="post">
          <input type="hidden" name="id" value="${shipment.id}" />
          <button type="submit">Cancel</button>
          <a href="manageShipment.jsp">Back</a>
        </form>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
