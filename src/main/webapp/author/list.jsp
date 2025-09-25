<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des auteurs</title>
    <link rel="stylesheet" href="css/style.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>
<nav class="blue-bg w-100 p-3">
    <div class="d-flex align-items-center gap-3">
        <p class="text-white fs-4 mb-0">
            <i class="fa-solid fa-book-open-reader" style="color: #ffffff;"></i> WebBiblio
        </p>
    </div>
</nav>
<div class="container mt-4">
    <h1>Liste des auteurs</h1>
    <table class="table table-striped">
        <thead class="table-primary">
        <tr><th>Prénom</th><th>Nom</th><th>Nationalité</th><th>Action</th></tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${authors}">
            <tr>
                <td>${a.firstName}</td>
                <td>${a.name}</td>
                <td>${a.nationality}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/authors" onsubmit="return confirm('Confirmer la suppression ?');" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="authorId" value="${a.id}"/>
                        <button type="submit" class="btn btn-danger btn-sm"><i class="fa-solid fa-trash" style="color: #ffffff;"></i></button>
                    </form>
                <form method="post" action="${pageContext.request.contextPath}/authors">
                    <input type="hidden" name="action" value="edit"/>
                    <input type="hidden" name="authorId" value="${a.id}"/>
                    <button type="submit" class="btn btn-warning btn-sm"><i class="fa-solid fa-pen-to-square" style="color: #ffffff;"></i></button>
                </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty authors}">
            <tr><td colspan="4" class="text-center">Aucun auteur enregistré.</td></tr>
        </c:if>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/author/form.jsp" class="btn btn-success">Ajouter un auteur</a>
    <a href="${pageContext.request.contextPath}/index" class="btn btn-secondary ms-2">Retour accueil</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>