# FavoriteArtists


Le projet Favorite Artists est un programme permettant de faire une liste de ses artistes, albums et titres favoris, et d'obtenir des informations complémentaires dessus (durée des morceaux par exemple) en s'appuyant sur l'API de Deezer et en stockant les choix de l'utilisateur dans une base de données locale.



### L'API Deezer ###

L'API de Deezer dispose de très nombreuses fonctionnalités. Elle stocke des informations sur ses utilisateurs et leurs playlists, contient des informations sur des genres, des radios...
Nous nous sommes concentrés sur trois fonctionnalités de l'API : récolter des informations sur des artistes, sur des albums, et sur des pistes.
Un URL de requête peut prendre par exemple cette forme : https://api.deezer.com/track/4301418


### Structure de notre base de données ###

![alt text](https://i.imgur.com/QI7SG3L.png)

### Les requêtes vers l'API ###

Nous effectuons deux types de requêtes en direction de l'API 

Nous pouvons dans un premier temps faire des recherches relativement imprécises, dans lesquelles on demandera à l'API de nous renvoyer l'artiste qu'elle considère le plus populaire parmi ceux qu'elle retrouve avec le nom d'artiste choisi par l'utilisateur.
Ces requêtes répondront majoritairement à des demandes de notre utilisateur, qui tatonnera dans la large librairie de Deezer pour y retrouver ses titres préférés.

Dans un second temps, nous pouvons réaliser des requêtes plus précises grâce aux informations obtenues par le biais des requêtes précédentes. Par exemple, via l'ID de l'artiste enregistré dans le JSON album de l'API, on peut en récupérer l'exact auteur.
Ces requêtes répondront majoritairement à des demandes de notre programme, qui fera en sorte de respecter la hiérarchie définie dans le MLD, et qui devra donc, lorsque l'utilisateur tentera d'ajouter un album, appeler l'API afin d'ajouter au préalable son auteur dans la base de données.

Les méthodes employées sont des méthodes static rassemblées dans l'objet RequetesAPI, qui renvoient un objet (modèle).
  - public static Artist artisteDeezer
  - public static Artist albumDeezer
  - public static Artist trackDeezer
  
Elles prennent en paramètre la connexion et soit un int (l'ID), soit un String(le nom).


### Les requêtes vers la base de données ###

Pour nos requêtes en direction de la base de données, le programme peut ajouter ou supprimer un artiste ou album individuel, afficher une table, et mettre à jour une piste. Puisqu'il n'est pas vraiment pertinent de modifier la plupart des données que nous stockons (La durée d'un morceau, l'auteur d'un album n'ont pas de raison d'être modifiées), la seule valeur que nous permettont d'update est le booléan 'favori'.

Les méthodes employées sont des méthodes static rassemblées dans l'objet RequetesJDBC, qui lisent ou mettent à jour la base de données. Les méthodes sont de type void, sauf les méthodes get (READ) qui renvoient un objet (modèle).
  - public static void createArtist
  - public static void deleteAlbum
  - public static Track getTrack
  - public static void updateTrack
  
  Elles prennent en paramètre la connexion et soit l'ID soit le nom d'un modèle à ajouter / consulter / mettre à jour / supprimer.

### Interface utilisateur ###

Au lancement de l'application, on propose à l'utilisateur de choisir la table qu'il veut afficher.
On attend un input de sa part, 1, 2 ou 3, et on affiche la table correspondante. Il dispose alors d'options CRUD.

C : L'utilisateur peut rechercher un artiste, un album ou un titre, dépendamment de la table qu'il est en train de consulter. S'il est dans l'API, une ligne sera ajoutée à la table correspondante.

R : L'utilisateur peut afficher les détails de la table. Read est aussi la méthode qui permet de se faire proposer un nouveau choix de table.

U : Ne fonctionne que sur la table des titres (pistes d'albums). Permet de mettre un titre en favori, ou de l'enlever des favoris.

D : L'utilisateur peut supprimer une ligne de la table en recopiant le nom de l'artiste, de l'album ou du titre


### Visuel ###

![alt text](https://i.imgur.com/GMe3TOH.png)

### Améliorations envisagées ###

On pourrait avoir un menu dans lequel on évolue de façon différente, par exemple permettant d'afficher moins d'infos des tables dans un premier temps, et de permettre à l'utilisateur de demander des détails. D'avoir par exemple juste la liste des artistes, et de pouvoir sélectionner l'un d'entre eux pour afficher son nombre de fans et la liste de ses albums enregistrés en base de donnée.

On devrait pouvoir refactoriser notre code aussi.
