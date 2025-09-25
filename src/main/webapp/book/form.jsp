<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title>Ajouter un Livre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center">Ajouter un Livre</h2>

    <form method="post" action="${pageContext.request.contextPath}/books">
        <input type="hidden" name="action" value="add"/>

        <div class="mb-3">
            <label for="title" class="form-label">Titre</label>
            <input type="text" id="title" name="title" class="form-control" placeholder="Titre du livre" required/>
        </div>

        <div class="mb-3">
            <label for="author" class="form-label">Auteur</label>
            <input type="text" id="author" name="author" class="form-control" placeholder="Nom de l'auteur" required/>
        </div>

        <div class="mb-3">
            <label for="isbn" class="form-label">ISBN</label>
            <input type="text" id="isbn" name="isbn" class="form-control" placeholder="ISBN" required/>
        </div>

        <div class="mb-3">
            <label for="publicationDate" class="form-label">Date</label>
            <input type="date" id="publicationDate" name="publicationDate" placeholder="Date de publication" required/>
        </div>

        <button type="submit" class="btn btn-primary">Valider</button>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>
</body>
</html>

