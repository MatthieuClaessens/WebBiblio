<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!-- Page JSP simple affichant la liste des utilisateurs et un formulaire d'ajout -->
<html>
<head><title>Library</title></head>
<body>
<h2>Add User</h2>
<form method="post" action="users">
    <input type="text" name="name" placeholder="Name"/>
    <button type="submit">Save</button>
</form>

<h2>User List</h2>
<ul>
    <c:forEach var="user" items="${users}">
        <li>${user.name}</li>
    </c:forEach>
</ul>
</body>
</html>
