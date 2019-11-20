# FavoriteArtists


Le projet Favorite Artist est un programme permettant de faire une liste de ses artistes, albums et titres favoris, et d'obtenir des informations complémentaires dessus (durée des morceaux par exemple) en s'appuyant sur l'API de Deezer et en stockant les choix de l'utilisateur dans une base de données locale.



L'API de Deezer dispose de très nombreuses fonctionnalitées. Elle stocke des informations sur ses utilisateurs et leurs playlists, contient des informations sur des genres, des radios...
Nous nous sommes concentrés sur trois fonctionnalités de l'API : récolter des informations sur des artistes, sur des albums, et sur des pistes.
Un URL de requête peut prendre par exemple cette forme : https://api.deezer.com/track/4301418

Décrire la structure de notre BDD (insérer diagramme merise)

![alt text](https://i.imgur.com/QI7SG3L.png)


Nous effectuons deux types de requêtes en direction de l'API 

Nous pouvons dans un premier temps faire des recherches relativement imprécise, dans lesquelles on demandera à l'API de nous renvoyer l'artiste qu'elle considère le plus populaire parmi ceux qu'elle retrouve avec le nom d'artiste choisi par l'utilisateur.
Ces requêtes répondront majoritairement à des demandes de notre utilisateur, qui tatonnera dans la large librairie de Deezer pour y retrouver ses titres préférés.

Dans un second temps, nous pouvons réaliser des requêtes plus précises grâce aux informations obtenues par le biais des requêtes précédentes. Par exemple, via l'ID de l'artiste enregistré dans le JSON album de l'API, on peut en récupérer l'exact auteur.
Ces requêtes répondront majoritairement à des demandes de notre programme, qui fera en sorte de respecter la hiérarchie définie dans le MLD, et qui devra donc, lorsque l'utilisateur tentera d'ajouter un album, appeler l'API afin d'ajouter au préalable son auteur dans la base de données.

Les méthodes employées sont des méthodes static rassemblées dans l'objet RequetesAPI, qui renvoient un objet (modèle).
  - public static Artist artisteDeezer
  - public static Artist albumDeezer
  - public static Artist trackDeezer
Elles prennent en paramètre la connexion et soit un int (l'ID), soit un String(le nom).


Pour nos requêtes en direction de la base de donnée, le programme peut ajouter ou supprimer un artiste ou album individuel, afficher une table, et mettre à jour une piste. Puisqu'il n'est pas vraiment pertinent de modifier la plupart des données que nous stockons (La durée d'un morceau, l'auteur d'un album n'ont pas de raison d'être modifiées), la seule valeur que nous 






Décrire notre interface.
