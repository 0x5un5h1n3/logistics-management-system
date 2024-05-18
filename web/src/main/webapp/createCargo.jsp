<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Create Cargo</title>
  </head>
  <body>
    <h1>Create Cargo</h1>
    <form action="createCargo" method="post">
      <label for="description">Description:</label>
      <input type="text" id="description" name="description" required />
      <br />
      <label for="weight">Weight:</label>
      <input type="number" id="weight" name="weight" step="0.01" required />
      <br />
      <button type="submit">Create</button>
    </form>
  </body>
</html>
