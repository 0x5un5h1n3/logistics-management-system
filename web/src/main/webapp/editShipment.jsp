<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Edit Shipment</title>
  </head>
  <body>
    <h1>Edit Shipment</h1>
    <form action="updateShipment" method="post">
      <input type="hidden" name="id" value="${shipment.id}" />
      <label for="origin">Origin:</label>
      <input
        type="text"
        id="origin"
        name="origin"
        value="${shipment.origin}"
        required
      />
      <br />
      <label for="destination">Destination:</label>
      <input
        type="text"
        id="destination"
        name="destination"
        value="${shipment.destination}"
        required
      />
      <br />
      <label for="shippingDate">Shipping Date:</label>
      <input
        type="date"
        id="shippingDate"
        name="shippingDate"
        value="${shipment.shippingDate}"
        required
      />
      <br />
      <button type="submit">Update</button>
    </form>
  </body>
</html>
