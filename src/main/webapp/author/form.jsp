<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Webbiblio - Auteur</title>
</head>
<body>
    <h2 class="text-center">X un auteur</h2>
    <form method="post" action="users">
        <input type="text" name="firstname" placeholder="Prénom"/>
        <input type="text" name="name" placeholder="Nom"/>
        <input type="text" name="nationality" placeholder="Nationalité"/>
        <input type="text" name="books" placeholder="Livre(s)"/>
        <button type="submit">Valider</button>
        <button type="submit"><a href="/index.jsp">Annuler</a></button>
    </form>
</body>
</html>
