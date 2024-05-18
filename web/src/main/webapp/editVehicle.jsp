<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Edit Vehicle</title>
  </head>
  <body>
    <h1>Edit Vehicle</h1>
    <form action="updateVehicle" method="post">
      <input type="hidden" name="id" value="${vehicle.id}" />
      <label for="type">Type:</label>
      <input
        type="text"
        id="type"
        name="type"
        value="${vehicle.type}"
        required
      />
      <br />
      <label for="licensePlate">License Plate:</label>
      <input
        type="text"
        id="licensePlate"
        name="licensePlate"
        value="${vehicle.licensePlate}"
        required
      />
      <br />
      <label for="capacity">Capacity:</label>
      <input
        type="number"
        id="capacity"
        name="capacity"
        value="${vehicle.capacity}"
        step="0.01"
        required
      />
      <br />
      <button type="submit">Update</button>
    </form>
  </body>
</html>
