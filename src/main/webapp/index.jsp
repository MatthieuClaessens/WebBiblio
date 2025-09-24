<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>WebBiblio</title>
    <link rel="stylesheet" href="css/style.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>
<nav class="blue-bg w-100 p-3">
    <div class="d-flex align-items-center gap-3">
        <p class="text-white fs-4 mb-0">
            <i class="fa-solid fa-book-open-reader" style="color: #ffffff;"></i> WebBiblio
        </p>
        <input type="text" class="form-control" style="width: 150px; height: 30px;" placeholder="Recherche..." />
    </div>
</nav>

<div class="container mt-4">

    <section id="author" class="mb-4">
        <h2>Auteurs</h2>
        <div class="d-flex mb-3">
            <form method="post" action="${pageContext.request.contextPath}/authors">
            <a href="author/form.jsp" class="btn btn-success me-2">Ajouter un auteur</a>
            <a href="authors" class="btn btn-secondary">Voir la liste complète</a>
            </form>
        </div>
        <table class="table table-striped table-bordered">
            <thead class="table-primary">
            <tr>
                <th>Prénom</th>
                <th>Nom</th>
                <th>Nationalité</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.firstName}</td>
                    <td>${author.name}</td>
                    <td>${author.nationality}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty authors}">
                <tr><td colspan="3" class="text-center text-muted">Aucun auteur enregistré.</td></tr>
            </c:if>
            </tbody>
        </table>
    </section>

    <section id="book" class="mb-4">
        <h2>Livres</h2>
        <div class="d-flex mb-3">
            <form method="post" action="${pageContext.request.contextPath}/books">
            <a href="book/form.jsp" class="btn btn-success me-2">Ajouter un livre</a>
            <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary">Voir la liste complète</a>
            </form>
        </div>
        <table class="table table-striped table-bordered">
            <thead class="table-primary">
            <tr>
                <th>Titre</th>
                <th>ISBN</th>
                <th>Date de publication</th>
                <th>Auteur</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.isbn}</td>
                    <td>${book.publicationDate}</td>
                    <td>${book.author.firstName} ${book.author.name}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty books}">
                <tr><td colspan="4" class="text-center text-muted">Aucun livre enregistré.</td></tr>
            </c:if>
            </tbody>
        </table>
    </section>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
