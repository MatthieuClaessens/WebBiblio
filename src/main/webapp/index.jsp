<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!-- Page JSP simple affichant la liste des utilisateurs et un formulaire d'ajout -->
<html>
<head>
    <title>WebBiblio</title></head>
<body>
<section id="author"> <!-- Section auteur !-->
    <div>
    <h2>Ajouter un auteur</h2>
        <a href="author/form.jsp"><button type="button">Ajouter</button></a>
        <a href="author/form.jsp"><button type="button">Modifier</button></a>
    </div>
</section>
<section id="book"> <!-- Ajout du livre !-->
    <div>
    <h2>Ajouter un livre</h2>
        <a href="book/form.jsp"><button type="button">Ajouter</button></a>
        <a href="book/form.jsp"><button type="button">Modifier</button></a>
    </div>
</section>

<section id="authorList">
    <div>
        <h2>Liste d'auteurs</h2>
        <ul>
            <c:forEach var="author" items="${author}">
                <li>${author.name}</li>
            </c:forEach>
        </ul>
    </div>
</section>
<section id="bookList">
    <div>
        <h2>Liste de livres</h2>
        <ul>
            <c:forEach var="book" items="${books}">
                <li>${book.name}</li>
            </c:forEach>
        </ul>
    </div>
</section>
</body>
</html>
