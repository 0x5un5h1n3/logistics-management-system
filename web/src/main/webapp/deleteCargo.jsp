<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <c:if test="${sessionScope.user != null}">
              <li><a href="index.jsp">Home</a></li>
              <li><a href="manageCargo.jsp">Manage Cargo</a></li>
              <li><a href="logout">Logout</a></li>
            </c:if>
            <c:if test="${sessionScope.user == null}">
              <li><a href="login.jsp">Login</a></li>
              <li><a href="register.jsp">Register</a></li>
            </c:if>
          </ul>
        </nav>
      </div>
    </header>
    <main>
      <div class="container">
        <c:if test="${sessionScope.user != null}">
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
        </c:if>
        <c:if test="${sessionScope.user == null}">
          <p>Please login or register to access this page.</p>
        </c:if>
      </div>
    </main>
    <footer>
      <div class="container">&copy; 2024 Logistics Management System</div>
    </footer>
    <script src="js/script.js"></script>
  </body>
</html>
