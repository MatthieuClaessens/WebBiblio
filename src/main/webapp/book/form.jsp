<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebBiblio - Livre</title>
</head>
<body>
    <h2 class="text-center">Ajouter un Livre</h2>
    <form method="post" action="books">
        <input type="text" name="title" placeholder="Titre du livre"/>
        <input type="text" name="author" placeholder="Nom de l'auteur"/>
        <input type="text" name="isbn" placeholder="ISBN"/>
        <input type="date" name="publicationDate" placeholder="Date de publication"/>
        <button type="submit">Valider</button>
        <button type="submit"><a href="/index.jsp">Annuler</a></button>
    </form>
</body>
</html>
