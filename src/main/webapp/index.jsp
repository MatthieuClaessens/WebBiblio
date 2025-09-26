<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>WebBiblio - Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="https://kit.fontawesome.com/ea9b6cde68.js" crossorigin="anonymous" defer></script>
</head>
<body>

<!-- Navbar -->
<div class="navbar-custom d-flex justify-content-center align-items-center gap-3">
    <a href="index.jsp" class="navbar-main">
        <i class="fa-solid fa-book-open-reader"></i> WebBiblio
    </a>
</div>


<!-- Conteneur principal du dashboard -->
<div class="dashboard-container">
    <!-- Wrapper des cartes pour auteurs et livres -->
    <div class="cards-wrapper">

        <!-- Carte Auteurs -->
        <div class="card-dashboard">
            <!-- Icône en haut de la carte -->
            <i class="fa-solid fa-user card-icon"></i>
            <!-- Titre de la carte -->
            <h5>Auteurs</h5>
            <!-- Description / info rapide -->
            <p>Gérez vos auteurs : ajoutez, modifiez ou supprimez facilement.</p>
            <!-- Boutons d'action : Liste et Ajouter -->
            <div class="d-flex justify-content-center gap-2">
                <!-- Bouton pour voir la liste des auteurs -->
                <a href="authors" class="btn btn-list btn-action">
                    <i class="fa-solid fa-list-ul me-1" style="color:white;"></i> Liste
                </a>
                <!-- Bouton pour ajouter un nouvel auteur -->
                <a href="author/form.jsp" class="btn btn-add btn-action">
                    <i class="fa-solid fa-user-plus me-1" style="color:white;"></i> Ajouter
                </a>
            </div>
        </div>

        <!-- Carte Livres -->
        <div class="card-dashboard">
            <!-- Icône en haut de la carte -->
            <i class="fa-solid fa-book card-icon"></i>
            <!-- Titre de la carte -->
            <h5>Livres</h5>
            <!-- Description / info rapide -->
            <p>Ajoutez de nouveaux livres ou consultez la liste complète.</p>
            <!-- Boutons d'action : Liste et Ajouter -->
            <div class="d-flex justify-content-center gap-2">
                <!-- Bouton pour voir la liste des livres -->
                <a href="books" class="btn btn-list btn-action">
                    <i class="fa-solid fa-list-ul me-1" style="color:white;"></i> Liste
                </a>
                <!-- Bouton pour ajouter un nouveau livre -->
                <a href="book/form.jsp" class="btn btn-add btn-action">
                    <i class="fa-solid fa-book me-1" style="color:white;"></i> Ajouter
                </a>
            </div>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
