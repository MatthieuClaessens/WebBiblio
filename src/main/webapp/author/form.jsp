<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Webbiblio - Auteur</title>
</head>
<body>
    <h2 class="text-center">X un auteur</h2>
    <form method="post" action="authors">
        <label for="firstname">test</label>
            <input type="text" name="firstname" placeholder="Prénom" required/>
        <label for="name">test</label>
            <input type="text" name="name" placeholder="Nom" required/>
        <label for="nationality">test</label>
            <input type="text" name="nationality" placeholder="Nationalité" required/>
        <label for="books">test</label>
            <input type="text" name="books" placeholder="Livre(s)"/>
        <button type="submit" name="action" value="add">Valider</button>
        <button><a href="${pageContext.request.contextPath}/index.jsp">Annuler</a></button>
    </form>
</body>
</html>
