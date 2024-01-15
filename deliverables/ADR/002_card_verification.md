# Mise en place de le vérification de cartes lors des tests de charge

* Statut : implémenté
* Décideurs : Mathieu Schalkwijk, Tobias Bonifay
* Date : 25/11/2023

## Contexte et énoncé du problème

Les tests de charge sont effectués sur des comptes avec des cartes de crédit ayant un numéro généré dans l'ordre
croissant. Ces numéros ne passe pas les conditions de vérification, que nous avons du enlevé pour pouvoir effectuer les
tests de charge. Le problème est que nous perdons un des seuls processus métier de notre architecture lors des tests de
charge, ce qui nous empêche de tester la cohérence de notre système.

## Facteurs de décision

* Avoir un processus reproductible lors des tests de charge
* Représenter le plus fidèlement possible le comportement du système en production

## Options envisagées

### Générer à l'avance un grand nombre de cartes
#### Avantages
* Permet de garder un processus métier lors des tests de charge
* Représente fidèlement le comportement du système en production (les utilisateurs n'utilisent pas 2500 fois la même
carte en un temps très réduit)
#### Inconvénients
* Nécessite de gérer la cohérence d'un grand nombre de données de test entre les requêtes et la base de données

### Faire toutes les transactions sur seul compte
#### Avantages
* Permet de ne pas avoir à gérer un grand nombre de données de test (charcher un seul utilisateur au démarage de la 
base de données est bien plus simple que 2500 utilisateurs)
#### Inconvénients
* Ne représente pas fidèlement le comportement du système en production (trop de requêtes sur la même zone de la base de
données)


## Solution retenue
La solution retenue est la première option (générer à l'avance un grand nombre de cartes). Le problème d'accès à la
même ressource en base de donnée est bien trop important pour être ignoré.  
**Implémentation**: Les données sont générées en amont des tests de charge dans un csv grâce au programme Java dans le
répertoire [DatabasePopulation](../../DatabasePopulation). Pour peupler la base de données, c'est cette même applicatoin
qui lit le csv et insère les données dans la base de données.