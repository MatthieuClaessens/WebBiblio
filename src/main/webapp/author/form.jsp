<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Webbiblio - Auteur</title>
</head>
<body>
    <h2 class="text-center">__ un auteur</h2>
    <form method="post" action="authors">
        <label for="firstname">Prénom
            <input type="text" name="firstname" placeholder="Prénom" required/>
        </label>
        <label for="name">Nom
            <input type="text" name="name" placeholder="Nom" required/>
        </label>
        <label for="nationality">Nationalité
            <input type="text" name="nationality" placeholder="Nationalité" required/>
        </label>
        <label for="books">Livre(s)
            <input type="text" name="books" placeholder="Livre(s)"/>
        </label>
        <button type="submit" name="action" value="add">Valider</button>
        <button><a href="${pageContext.request.contextPath}/index.jsp">Annuler</a></button>
    </form>
</body>
</html>
