<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>
        <!-- Choix dynamique du titre selon si on modifie ou ajoute un auteur -->
        <c:choose>
            <c:when test="${not empty author}">Modifier un auteur</c:when>
            <c:otherwise>Ajouter un auteur</c:otherwise>
        </c:choose>
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
    <a href="${pageContext.request.contextPath}/authors" class="navbar-secondary">Liste d'auteurs</a>
</div>

<!-- Conteneur principal centré -->
<div class="min-vh-100 d-flex align-items-center justify-content-center">
    <div class="card-dashboard" style="width: 100%; max-width: 520px; padding: 2rem;">
        <h5 class="text-center mb-4">
            <c:choose>
                <c:when test="${not empty author}">Modifier un auteur</c:when>
                <c:otherwise>Ajouter un auteur</c:otherwise>
            </c:choose>
        </h5>

        <!-- Affichage d'une erreur si présente -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <!-- Formulaire POST -->
        <form method="post" action="${pageContext.request.contextPath}/authors">
            <!-- Champ caché pour l'action : 'edit' ou 'add' -->
            <input type="hidden" name="action" value="${not empty author ? 'edit' : 'add'}"/>

            <!-- Champ caché pour l'ID si modification -->
            <c:if test="${not empty author}">
                <input type="hidden" name="authorId" value="${author.id}"/>
            </c:if>

            <!-- Prénom -->
            <div class="mb-3">
                <label for="firstname" class="form-label">Prénom</label>
                <input type="text" id="firstname" name="firstname" class="form-control" placeholder="Prénom"
                       value="${not empty author ? author.firstname : param.firstname}" required />
            </div>

            <!-- Nom -->
            <div class="mb-3">
                <label for="name" class="form-label">Nom</label>
                <input type="text" id="name" name="name" class="form-control" placeholder="Nom"
                       value="${not empty author ? author.name : param.name}" required />
            </div>

            <!-- Nationalité -->
            <div class="mb-3">
                <label for="nationality" class="form-label">Nationalité</label>
                <input type="text" id="nationality" name="nationality" class="form-control" placeholder="Nationalité"
                       value="${not empty author ? author.nationality : param.nationality}" />
            </div>

            <!-- Livres séparés par virgule -->
            <div class="mb-4">
                <label for="books" class="form-label">Livres (séparés par virgule)</label>
                <input type="text" id="books" name="books" class="form-control"
                       placeholder="Livre1, Livre2, ..." value="${not empty booksStr ? booksStr : param.books}" />
                <small class="text-muted">Optionnel : créer des livres basiques associés à cet auteur</small>
            </div>

            <!-- Boutons Valider et Annuler -->
            <div class="d-flex justify-content-center gap-3">
                <button type="submit" class="btn btn-success btn-action">
                    <i class="fa-solid fa-check me-1"></i>
                    <c:choose>
                        <c:when test="${not empty author}">Modifier</c:when>
                        <c:otherwise>Ajouter</c:otherwise>
                    </c:choose>
                </button>
                <a href="${pageContext.request.contextPath}/authors" class="btn btn-secondary btn-action">
                    <i class="fa-solid fa-xmark me-1"></i> Annuler
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
