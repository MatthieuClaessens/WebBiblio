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
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.titre}</td>
                <td>${book.isbn}</td>
                <td>${book.datePublication}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="post" action="${pageContext.request.contextPath}/authors">
        <button type="submit" name="action" value="delete">Supprimer</button>
    </form>
    <form method="get" action="${pageContext.request.contextPath}/form.jsp">
        <button type="submit">Ajouter</button>
    </form>
</div>
</body>
</html>
