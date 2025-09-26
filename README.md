# WebBiblio 📚

Application Jakarta EE déployée sur **Apache Tomcat**, avec une base de données **PostgreSQL** et une interface d’administration via **Adminer**.

---

## 🚀 Prérequis

- [Docker](https://docs.docker.com/get-docker/) installé  
- [Docker Compose](https://docs.docker.com/compose/) installé  
- Java 17+ (si recompilation du projet)  
- Maven (si reconstruction du fichier `.war`)  

<div align="center">
  <table>
    <tr>
      <td align="center" width="50%">
        <img height="200" src="https://i.ibb.co/tPkLgHcn/index.png" alt="Index / Main page" />
      </td>
      <td align="center" width="50%">
        <img height="200" src="https://i.ibb.co/p6wS84P2/list.png" alt="Author list" />
      </td>
    </tr>
    <tr>
      <td align="center" width="50%">
        <img height="200" src="https://i.ibb.co/QvjWShL0/form.png" alt="Edit author form" />
      </td>
      <td align="center" width="50%">
        <img height="200" src="https://i.ibb.co/qLf0wH5H/add.png" alt="Add author form" />
      </td>
    </tr>
    <tr>
      <td align="center" width="50%">
        <img height="200" src="https://i.ibb.co/zh2c4mmY/editbook2.png" alt="Edit Book form" />
      </td>
      <td align="center" width="50%">
        <!-- Ajoute une autre image ici si besoin, ou laisse vide -->
      </td>
    </tr>
  </table>
</div>

---

## 📂 Structure du projet

```text
src/
└── main/
    ├── java/
    │   └── com/monprojet/biblio/
    │       ├── model/       # Entités
    │       ├── dao/         # Accès aux données
    │       ├── service/     # Logique métier
    │       └── servlet/     # Contrôleurs web
    ├── resources/
    │   └── META-INF/persistence.xml  # Configuration JPA
    └── webapp/
        ├── book/           # Pages liées aux livres
        │   ├── list.jsp    # Liste des livres
        │   └── form.jsp    # Formulaire livre
        ├── author/         # Pages liées aux auteurs
        │   ├── list.jsp    # Liste des auteurs
        │   └── form.jsp    # Formulaire auteur
        └── index.jsp       # Page d'accueil
```


## 🐳 Exécution avec Docker

### Construction de l’image
```bash
docker build -t jakarta-tomcat-app-tomcat .
```

### Lancement des conteneurs

```bash
docker compose up -d
```

## 💾 Sauvegarde et restauration

### Sauvegarde des images dans une archive ``.tar`` :
```bash
docker save -o WebBiblioAll.tar jakarta-tomcat-app-tomcat postgres:15 adminer
```

### Chargement des images dans un autre projet :

```bash
docker load -i WebBiblioAll.tar
```

## 🌐 Accès aux services

- Application Tomcat (WebBiblio) : http://localhost:8080/webbiblio
- Interface Adminer : http://localhost:8081
- PostgreSQL : accessible sur localhost:5433

## 🧹 Nettoyage

### Arrêt et suppression des conteneurs et volumes :

```bash
docker compose down -v
```

### Suppression manuelle des conteneurs et volumes :

```bash
docker rm -f tomcat_app adminer_ui postgres_db
```
```bash
docker volume rm pgdata
```
