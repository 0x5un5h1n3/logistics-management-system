<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Manage Cargo</title>
  </head>
  <body>
    <h1>Manage Cargo</h1>
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
    <a href="createCargo.jsp">Create New Cargo</a>
  </body>
</html>
