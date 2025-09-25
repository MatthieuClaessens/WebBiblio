<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Livre des livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>
<div class="container mt-4">
    <h1>Liste des livres</h1>
    <table class="table table-striped">
        <thead class="table-primary">
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
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/books" onsubmit="return confirm('Confirmer la suppression ?');" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <!-- <input type="hidden" name="authorId" value="${a.id}"/> -->
                        <button type="submit" class="btn btn-danger btn-sm"><i class="fa-solid fa-trash" style="color: #ffffff;"></i></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty books}">
            <tr><td colspan="4" class="text-center">Aucun livre enregistré.</td></tr>
        </c:if>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/book/form.jsp" class="btn btn-success">Ajouter un livre</a>
    <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary ms-2">Retour accueil</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

