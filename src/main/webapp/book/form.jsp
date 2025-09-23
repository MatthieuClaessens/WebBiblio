<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebBiblio - Livre</title>
</head>
<body>
    <h2 class="text-center">__ un Livre</h2>
    <form method="post" action="books">
        <label for="title">test
        <input type="text" name="title" placeholder="Titre du livre" required/>
        </label>
        <label for="author">test
        <input type="text" name="author" placeholder="Nom de l'auteur" required/>
        </label>
        <label for="isbn">test
        <input type="text" name="isbn" placeholder="ISBN" required/>
        </label>
        <label for="publicationDate">test
        <input type="date" name="publicationDate" placeholder="Date de publication" required/>
        </label>
        <button type="submit" name="action" value="add">Valider</button>
        <button><a href="${pageContext.request.contextPath}/index.jsp">Annuler</a></button>
    </form>
</body>
</html>
