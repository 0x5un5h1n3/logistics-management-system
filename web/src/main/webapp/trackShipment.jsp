<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Track Shipment</title>
  </head>
  <body>
    <h1>Track Shipment</h1>
    <form action="trackShipment" method="post">
      <label for="shipmentId">Shipment ID:</label>
      <input type="text" id="shipmentId" name="shipmentId" />
      <button type="submit">Track</button>
    </form>

    <c:if test="${not empty shipment}">
      <h2>Shipment Details</h2>
      <p>ID: ${shipment.id}</p>
      <p>Origin: ${shipment.origin}</p>
      <p>Destination: ${shipment.destination}</p>
      <p>Shipping Date: ${shipment.shippingDate}</p>
    </c:if>
  </body>
</html>
