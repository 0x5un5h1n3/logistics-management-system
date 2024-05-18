<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Delete Cargo</title>
  </head>
  <body>
    <h1>Delete Cargo</h1>
    <p>Are you sure you want to delete the following cargo?</p>
    <p>ID: ${cargo.id}</p>
    <p>Description: ${cargo.description}</p>
    <p>Weight: ${cargo.weight}</p>
    <form action="deleteCargo" method="post">
      <input type="hidden" name="id" value="${cargo.id}" />
      <button type="submit">Delete</button>
      <a href="manageCargo.jsp">Cancel</a>
    </form>
  </body>
</html>
