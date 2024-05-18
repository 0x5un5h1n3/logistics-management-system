<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Edit Shipment</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Edit Shipment</h1>
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="manageShipment.jsp">Manage Shipments</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="container">
        <form id="editShipmentForm">
          <input
            type="hidden"
            id="shipmentId"
            name="shipmentId"
            value="${shipment.id}"
          />
          <div class="form-group">
            <label for="origin">Origin:</label>
            <input
              type="text"
              id="origin"
              name="origin"
              value="${shipment.origin}"
              required
            />
          </div>
          <div class="form-group">
            <label for="destination">Destination:</label>
            <input
              type="text"
              id="destination"
              name="destination"
              value="${shipment.destination}"
              required
            />
          </div>
          <div class="form-group">
            <label for="shippingDate">Shipping Date:</label>
            <input
              type="date"
              id="shippingDate"
              name="shippingDate"
              value="${shipment.shippingDate}"
              required
            />
          </div>
          <button type="submit" class="btn">Update Shipment</button>
        </form>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
