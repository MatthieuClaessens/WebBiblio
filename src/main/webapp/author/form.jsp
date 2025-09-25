<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>
        <c:choose>
            <c:when test="${not empty author}">Modifier</c:when>
            <c:otherwise>Ajouter</c:otherwise>
        </c:choose> un auteur
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>

<div class="navbar-custom">
    <a href="index.jsp"><i class="fa-solid fa-book-open-reader"></i>WebBiblio</a>
</div>

<div class="dashboard-container">
    <div class="card-dashboard" style="max-width: 500px; width: 100%; padding: 2rem;">
        <h5 class="text-center mb-4">
            <c:choose>
                <c:when test="${not empty author}">Modifier un auteur</c:when>
                <c:otherwise>Ajouter un auteur</c:otherwise>
            </c:choose>
        </h5>

        <form method="post" action="${pageContext.request.contextPath}/authors">
            <!-- Action et authorId -->
            <c:choose>
                <c:when test="${not empty author}">
                    <input type="hidden" name="action" value="edit"/>
                    <input type="hidden" name="authorId" value="${author.id}"/>
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="action" value="add"/>
                </c:otherwise>
            </c:choose>

            <div class="mb-3">
                <label for="firstname" class="form-label">Prénom</label>
                <input type="text" id="firstname" name="firstname" class="form-control"
                       placeholder="Prénom" value="${author.firstName}" required/>
            </div>

            <div class="mb-3">
                <label for="name" class="form-label">Nom</label>
                <input type="text" id="name" name="name" class="form-control"
                       placeholder="Nom" value="${author.name}" required/>
            </div>

            <div class="mb-3">
                <label for="nationality" class="form-label">Nationalité</label>
                <input type="text" id="nationality" name="nationality" class="form-control"
                       placeholder="Nationalité" value="${author.nationality}" required/>
            </div>

            <div class="mb-3">
                <label for="books" class="form-label">Livres (séparés par virgule)</label>
                <c:choose>
                    <c:when test="${not empty author}">
                        <c:set var="booksStr" value="" />
                        <c:forEach var="b" items="${author.books}" varStatus="s">
                            <c:set var="booksStr" value="${booksStr}${b.title}${!s.last ? ', ' : ''}" />
                        </c:forEach>
                        <input type="text" id="books" name="books" class="form-control"
                               placeholder="Livre1, Livre2, ..." value="${booksStr}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="books" name="books" class="form-control"
                               placeholder="Livre1, Livre2, ..." value=""/>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="d-flex justify-content-center gap-3 mt-4">
                <button type="submit" class="btn btn-add btn-action">
                    <i class="fa-solid fa-check me-1"></i> Valider
                </button>
                <a href="${pageContext.request.contextPath}/authors" class="btn btn-list btn-action">
                    <i class="fa-solid fa-xmark me-1"></i> Annuler
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
