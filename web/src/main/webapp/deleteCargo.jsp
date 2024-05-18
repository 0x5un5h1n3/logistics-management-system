<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Delete Cargo</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Delete Cargo</h1>
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
        <h2>Are you sure you want to delete this cargo?</h2>
        <div class="cargo-details">
          <p>ID: ${cargo.id}</p>
          <p>Description: ${cargo.description}</p>
          <p>Weight: ${cargo.weight}</p>
        </div>
        <form id="deleteCargoForm">
          <input
            type="hidden"
            id="cargoId"
            name="cargoId"
            value="${cargo.id}"
          />
          <button type="submit" class="btn btn-danger">Delete Cargo</button>
          <a href="manageCargo.jsp" class="btn">Cancel</a>
        </form>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
