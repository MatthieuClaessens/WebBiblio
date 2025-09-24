<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Livre - liste</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="table_component" role="region" tabindex="0">
    <table>
        <caption>Liste des livres</caption>
        <thead>
        <tr>
            <th>Titre</th>
            <th>ISBN</th>
            <th>Date de publication</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="b" items="${books}">
            <tr>
                <td>${b.id}</td>
                <td>${b.title}</td>
                <td>${b.isbn}</td>
                <td>${b.publicationDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="post" action="${pageContext.request.contextPath}/books">
        <button type="submit" name="action" value="delete">Supprimer</button>
    </form>
    <form method="get" action="${pageContext.request.contextPath}/book/form.jsp">
        <button type="submit">Ajouter</button>
    </form>
</div>
</body>
</html>
