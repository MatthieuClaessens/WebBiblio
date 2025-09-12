<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!-- Page JSP simple affichant la liste des utilisateurs et un formulaire d'ajout -->
<html>
<head><title>Auteurs</title></head>
<body>
<h2>Ajouter un auteur</h2>
<form method="post" action="users">
    <input type="text" name="name" placeholder="Name"/>
    <button type="submit">Save</button>
</form>

<h2>Liste d'auteur</h2>
<ul>
    <c:forEach var="user" items="${users}">
        <li>${user.name}</li>
    </c:forEach>
</ul>
</body>
</html>
