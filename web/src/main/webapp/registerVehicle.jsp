<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Register Vehicle</title>
  </head>
  <body>
    <h1>Register Vehicle</h1>
    <form action="registerVehicle" method="post">
      <label for="type">Type:</label>
      <input type="text" id="type" name="type" required />
      <br />
      <label for="licensePlate">License Plate:</label>
      <input type="text" id="licensePlate" name="licensePlate" required />
      <br />
      <label for="capacity">Capacity:</label>
      <input type="number" id="capacity" name="capacity" step="0.01" required />
      <br />
      <button type="submit">Register</button>
    </form>
  </body>
</html>
