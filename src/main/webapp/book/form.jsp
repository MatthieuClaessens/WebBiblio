<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>
        <c:choose>
            <c:when test="${not empty book}">Modifier</c:when>
            <c:otherwise>Ajouter</c:otherwise>
        </c:choose> un livre
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>

<!-- Navbar -->
<div class="navbar-custom d-flex justify-content-center align-items-center gap-3">
    <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-secondary">Accueil</a>
    <a href="${pageContext.request.contextPath}/index.jsp" class="navbar-main">
        <i class="fa-solid fa-book-open-reader"></i> WebBiblio
    </a>
    <a href="${pageContext.request.contextPath}/books" class="navbar-secondary">Liste de livres</a>
</div>

<div class="min-vh-100 d-flex align-items-center justify-content-center">
    <div class="card-dashboard" style="width: 100%; max-width: 520px; padding: 2rem;">
        <h5 class="text-center mb-1">
            <c:choose>
                <c:when test="${not empty book}">Modifier un livre</c:when>
                <c:otherwise>Ajouter un livre</c:otherwise>
            </c:choose>
        </h5>

        <!-- Formulaire POST -->
        <form method="post" action="${pageContext.request.contextPath}/books">
            <input type="hidden" name="action" value="${not empty book ? 'edit' : 'add'}"/>
            <c:if test="${not empty book}">
                <input type="hidden" name="bookId" value="${book.id}"/>
            </c:if>

            <!-- Titre -->
            <div class="mb-3">
                <label for="title" class="form-label">Titre</label>
                <input type="text" id="title" name="title" class="form-control"
                       placeholder="Titre du livre" value="${not empty book ? book.title : ''}" required/>
            </div>

            <!-- ISBN -->
            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <input type="text" id="isbn" name="isbn" class="form-control"
                       placeholder="Numéro ISBN" value="${not empty book ? book.isbn : ''}" required/>
            </div>

            <!-- Date de publication -->
            <div class="mb-3">
                <label for="publicationDate" class="form-label">Date de publication</label>
                <input type="date" id="publicationDate" name="publicationDate" class="form-control"
                       value="${not empty book && not empty book.publicationDate ? book.publicationDate : ''}" required/>
            </div>

            <!-- Sélection de l'auteur -->
            <div class="mb-4">
                <label for="authorId" class="form-label">Auteur</label>
                <select id="authorId" name="authorId" class="form-select" required>
                    <option value="">-- Sélectionner un auteur --</option>
                    <!-- Correction : ${a.firstname} au lieu de ${a.firstName} -->
                    <c:forEach var="a" items="${authors}">
                        <option value="${a.id}"
                                <c:if test="${not empty book && book.author != null && book.author.id == a.id}">selected</c:if>>
                                ${a.firstname} ${a.name}
                        </option>
                    </c:forEach>
                </select>
                <c:if test="${empty authors}">
                    <small class="text-danger">Aucun auteur disponible. Créer d’abord un auteur.</small>
                </c:if>
            </div>

            <!-- Boutons Valider / Annuler -->
            <div class="d-flex justify-content-center gap-3">
                <button type="submit" class="btn btn-success btn-action">
                    <i class="fa-solid fa-check me-1"></i> Valider
                </button>
                <a href="${pageContext.request.contextPath}/books" class="btn btn-secondary btn-action">
                    <i class="fa-solid fa-xmark me-1"></i> Annuler
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
