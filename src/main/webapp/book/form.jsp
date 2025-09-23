<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebBiblio - Livre</title>
</head>
<body>
    <h2 class="text-center">Ajouter un Livre</h2>
    <form method="post" action="books">
        <label for="title">test</label>
        <input type="text" name="title" placeholder="Titre du livre" required/>
        <label for="author">test</label>
        <input type="text" name="author" placeholder="Nom de l'auteur" required/>
        <label for="isbn">test</label>
        <input type="text" name="isbn" placeholder="ISBN" required/>
        <label for="publicationDate">test</label>
        <input type="date" name="publicationDate" placeholder="Date de publication" required/>
        <button type="submit" name="action" value="add">Valider</button>
        <button><a href="${pageContext.request.contextPath}/index.jsp">Annuler</a></button>
    </form>
</body>
</html>
