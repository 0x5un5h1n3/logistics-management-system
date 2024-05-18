<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Register Vehicle</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Register Vehicle</h1>
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="manageVehicles.jsp">Manage Vehicles</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="container">
        <form action="registerVehicle" method="post">
          <div class="form-group">
            <label for="type">Type:</label>
            <input type="text" id="type" name="type" required />
          </div>
          <div class="form-group">
            <label for="licensePlate">License Plate:</label>
            <input type="text" id="licensePlate" name="licensePlate" required />
          </div>
          <div class="form-group">
            <label for="capacity">Capacity:</label>
            <input
              type="number"
              id="capacity"
              name="capacity"
              step="0.01"
              required
            />
          </div>
          <button type="submit" class="btn">Register</button>
        </form>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
