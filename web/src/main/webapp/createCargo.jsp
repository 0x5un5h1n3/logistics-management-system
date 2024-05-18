<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Create Cargo</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Create Cargo</h1>
        <nav>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="manageCargo.jsp">Manage Cargo</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="container">
        <form id="cargoForm">
          <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" id="description" name="description" required />
          </div>
          <div class="form-group">
            <label for="weight">Weight:</label>
            <input
              type="number"
              id="weight"
              name="weight"
              step="0.01"
              required
            />
          </div>
          <button type="submit" class="btn">Create Cargo</button>
        </form>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
