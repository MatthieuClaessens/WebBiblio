<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des livres</title>
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

<!-- Conteneur principal -->
<div class="dashboard-container" style="flex-direction: column; gap: 2rem;">
    <div class="card-dashboard" style="width: 100%; max-width: 900px; padding: 2rem;">
        <h5 class="text-center mb-4">Liste des livres</h5>

        <!-- Affichage des erreurs de session -->
        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-danger">${sessionScope.errorMessage}</div>
            <c:remove var="errorMessage" scope="session"/>
        </c:if>

        <!-- Tableau -->
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle" id="booksTable">
                <thead class="table-primary">
                <tr>
                    <th>Titre</th>
                    <th>ISBN</th>
                    <th>Date</th>
                    <th>Auteur</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="b" items="${books}">
                    <tr data-id="${b.id}">
                        <td>${b.title}</td>
                        <td>${b.isbn}</td>
                        <td>${b.publicationDate}</td>
                        <td>
                            <c:choose>
                                <c:when test="${b.author != null}">
                                    ${b.author.firstname} ${b.author.name}
                                </c:when>
                                <c:otherwise><span class="text-muted">—</span></c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty books}">
                    <tr><td colspan="4" class="text-center">Aucun livre enregistré.</td></tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <!-- Bloc d'action -->
        <div class="d-flex justify-content-center gap-3 mt-4" id="actionButtons" style="display: none;">
            <a id="btnEdit" href="#" class="btn btn-warning btn-action">
                <i class="fa-solid fa-pen-to-square me-1"></i> Modifier
            </a>

            <form id="formDelete" method="post" action="${pageContext.request.contextPath}/books" style="display: inline;">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="bookId" id="deleteId"/>
                <button type="submit" class="btn btn-danger btn-action" onclick="return confirm('Confirmer la suppression ?');">
                    <i class="fa-solid fa-trash me-1"></i> Supprimer
                </button>
            </form>

            <a href="${pageContext.request.contextPath}/authors?action=add" class="btn btn-success btn-action">
                <i class="fa-solid fa-user-plus me-1"></i> Ajouter
            </a>
        </div>

    </div>
</div>

<!-- Script de sélection -->
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const table = document.getElementById('booksTable');
    const actionDiv = document.getElementById('actionButtons');
    let selectedRow = null;

    table.querySelectorAll('tbody tr[data-id]').forEach(row => {
        row.addEventListener('click', () => {
            if (selectedRow) selectedRow.classList.remove('selected');

            if (selectedRow === row) {
                selectedRow = null;
                actionDiv.style.display = 'none';
            } else {
                selectedRow = row;
                row.classList.add('selected');
                actionDiv.style.display = 'flex';

                const id = row.dataset.id;
                document.getElementById('btnEdit').href = contextPath + "/books?action=edit&bookId=" + id;
                document.getElementById('deleteId').value = id;
            }
        });
    });
</script>

<style>
    .selected {
        background-color: #e3f2fd !important;
    }
    tr[data-id]:hover {
        cursor: pointer;
    }
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
