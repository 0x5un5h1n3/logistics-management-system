<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Edit Cargo</title>
  </head>
  <body>
    <h1>Edit Cargo</h1>
    <form action="updateCargo" method="post">
      <input type="hidden" name="id" value="${cargo.id}" />
      <label for="description">Description:</label>
      <input
        type="text"
        id="description"
        name="description"
        value="${cargo.description}"
        required
      />
      <br />
      <label for="weight">Weight:</label>
      <input
        type="number"
        id="weight"
        name="weight"
        value="${cargo.weight}"
        step="0.01"
        required
      />
      <br />
      <button type="submit">Update</button>
    </form>
  </body>
</html>
