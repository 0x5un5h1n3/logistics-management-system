<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Deregister Vehicle</title>
  </head>
  <body>
    <h1>Deregister Vehicle</h1>
    <p>Are you sure you want to deregister the following vehicle?</p>
    <p>ID: ${vehicle.id}</p>
    <p>Type: ${vehicle.type}</p>
    <p>License Plate: ${vehicle.licensePlate}</p>
    <p>Capacity: ${vehicle.capacity}</p>
    <form action="deregisterVehicle" method="post">
      <input type="hidden" name="id" value="${vehicle.id}" />
      <button type="submit">Deregister</button>
      <a href="manageVehicles.jsp">Cancel</a>
    </form>
  </body>
</html>
