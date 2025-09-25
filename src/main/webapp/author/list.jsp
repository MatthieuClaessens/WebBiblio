<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des auteurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>

<!-- Navbar simple avec icône et nom de l'application -->
<div class="navbar-custom">
    <a href="index.jsp"><i class="fa-solid fa-book-open-reader"></i>WebBiblio</a>
</div>

<!-- Conteneur principal du dashboard -->
<div class="dashboard-container" style="flex-direction: column; gap: 2rem;">
    <!-- Carte contenant le tableau -->
    <div class="card-dashboard" style="width: 100%; max-width: 800px; padding: 2rem;">
        <!-- Titre de la section -->
        <h5 class="text-center mb-4">Liste des auteurs</h5>

        <!-- Tableau responsive -->
        <div class="table-responsive">
            <table class="table table-striped table-hover align-middle" id="authorsTable">
                <thead class="table-primary">
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Nationalité</th>
                </tr>
                </thead>
                <tbody>
                <!-- Boucle sur tous les auteurs -->
                <c:forEach var="a" items="${authors}">
                    <!-- On stocke les données dans des attributs data-* pour JS -->
                    <tr data-id="${a.id}" data-firstname="${a.firstName}" data-name="${a.name}" data-nationality="${a.nationality}">
                        <td>${a.firstName}</td>
                        <td>${a.name}</td>
                        <td>${a.nationality}</td>
                    </tr>
                </c:forEach>

                <!-- Message si aucun auteur -->
                <c:if test="${empty authors}">
                    <tr>
                        <td colspan="3" class="text-center">Aucun auteur enregistré.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <!-- Boutons d'action : Modifier, Supprimer, Ajouter -->
        <!-- Cachés par défaut et affichés uniquement quand une ligne est sélectionnée -->
        <div class="d-flex justify-content-center gap-3 mt-4" id="actionButtons" style="display: none;">
            <!-- Bouton Modifier -->
            <a id="btnEdit" href="#" class="btn btn-warning btn-action">
                <i class="fa-solid fa-pen-to-square me-1"></i> Modifier
            </a>

            <!-- Formulaire pour Supprimer -->
            <form id="formDelete" method="post" action="${pageContext.request.contextPath}/authors">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="authorId" id="deleteId"/>
                <button type="submit" class="btn btn-danger btn-action" onclick="return confirm('Confirmer la suppression ?');">
                    <i class="fa-solid fa-trash me-1"></i> Supprimer
                </button>
            </form>

            <!-- Bouton Ajouter (toujours visible) -->
            <a href="${pageContext.request.contextPath}/authors?action=add" class="btn btn-add btn-action">
                <i class="fa-solid fa-user-plus me-1"></i> Ajouter
            </a>
        </div>

    </div>
</div>

<!-- Script pour gérer la sélection de ligne et l'affichage des boutons -->
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const table = document.getElementById('authorsTable');
    const actionDiv = document.getElementById('actionButtons');
    let selectedRow = null;

    // On ajoute un listener sur chaque ligne du tableau
    table.querySelectorAll('tbody tr').forEach(row => {
        row.addEventListener('click', () => {
            // On désélectionne l'ancienne ligne si nécessaire
            if(selectedRow) selectedRow.classList.remove('selected');

            // Si on reclique sur la même ligne, on la désélectionne
            if(selectedRow === row) {
                selectedRow = null;
                actionDiv.style.display = 'none';
            } else {
                // Sinon, on sélectionne la nouvelle ligne
                selectedRow = row;
                row.classList.add('selected');
                actionDiv.style.display = 'flex';

                const id = row.dataset.id;
                // Met à jour le lien Modifier avec l’ID de l’auteur sélectionné
                document.getElementById('btnEdit').href = contextPath + "/authors?action=edit&authorId=" + id;
                // Met à jour le champ caché pour la suppression
                document.getElementById('deleteId').value = id;
            }
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
