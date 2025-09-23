<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>Liste des auteurs</title>
    <link rel="stylesheet" href="css/style.css" />
</head>
<body>
<div class="table_component" role="region" tabindex="0">
    <table>
        <caption>Liste des auteurs</caption>
        <thead>
        <tr>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Nationalité</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${authors}">
            <tr>
                <td>${a.firstName}</td>
                <td>${a.name}</td>
                <td>${a.nationality}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/authors" style="margin:0;">
                        <input type="hidden" name="action" value="delete" />
                        <input type="hidden" name="authorId" value="${a.id}" />
                        <button type="submit">Supprimer</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <form method="get" action="${pageContext.request.contextPath}/author/form.jsp">
        <button type="submit">Ajouter</button>
    </form>
</div>
</body>
</html>
