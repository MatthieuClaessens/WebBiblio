<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <title>Ajouter un auteur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-5">
    <h2>Ajouter un auteur</h2>

    <form method="post" action="${pageContext.request.contextPath}/authors">
        <input type="hidden" name="action" value="add" />

        <div class="mb-3">
            <label for="firstname" class="form-label">Prénom</label>
            <input type="text" id="firstname" name="firstname" class="form-control" placeholder="Prénom" required />
        </div>

        <div class="mb-3">
            <label for="name" class="form-label">Nom</label>
            <input type="text" id="name" name="name" class="form-control" placeholder="Nom" required />
        </div>

        <div class="mb-3">
            <label for="nationality" class="form-label">Nationalité</label>
            <input type="text" id="nationality" name="nationality" class="form-control" placeholder="Nationalité" required />
        </div>

        <div class="mb-3">
            <label for="books" class="form-label">Livres (séparés par virgule)</label>
            <input type="text" id="books" name="books" class="form-control" placeholder="Livre1, Livre2, ..." />
        </div>

        <button type="submit" class="btn btn-primary">Valider</button>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary ms-2">Annuler</a>
    </form>
</div>
</body>
</html>
