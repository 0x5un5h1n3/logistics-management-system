<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Manage Cargo</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css" />
  </head>
  <body>
    <header>
      <div class="container">
        <h1>Manage Cargo</h1>
        <nav>
          <ul>
            <c:if test="${sessionScope.user != null}">
              <li><a href="index.jsp">Home</a></li>
              <li><a href="createCargo.jsp">Create New Cargo</a></li>
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
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Weight</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="cargo" items="${cargos}">
                <tr>
                  <td>${cargo.id}</td>
                  <td>${cargo.description}</td>
                  <td>${cargo.weight}</td>
                  <td>
                    <a href="editCargo?id=${cargo.id}">Edit</a>
                    <a href="deleteCargo?id=${cargo.id}">Delete</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <a href="createCargo.jsp" class="btn">Create New Cargo</a>
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
