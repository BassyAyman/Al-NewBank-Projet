# Mise en place d'un Hot replica pour la base de donnée Redis local a la region.

* Statut : proposé 
* Date : 03/12/2023

***Histoire technique*** : Afin de realiser un systeme fortement coherent et permettant de prendre en charge un grand nombre de transaction
succesive pour un meme utilisateur, il a été decider de mettre en place une base de donnée Redis afin de stocker les debits clients.

## Contexte et énoncé du problème
Le probleme et que la base de donnée Redis mise en place et d'une grande importance dans notre systeme.
Un grand probleme serai que la base de donnée meurt, en effet l'on perdrai toute nos information sur les clients resultant sur de potentiel forte incoherence
de notre systeme, resultant forcement sur un arret pure et symple de la posibilité d'effectuer une transaction sur le Web. En effet nous serions forcé de bloquer
le tout le temps de relancer la base de donnée et de copier les données les plus recente depuis le slave vers redis avant de pouvoir etre de nouveau disponible.

## Options envisagées
### Mettre à jour redis et postgres master progressivement pour chaque client
Mettre en place un Hot replica de la base de donnée redis **LOCAL** a la region.
#### Avantages
* Permettra de repliquer les données et de switcher de base de donnée en cas de crash du master Redis.
* L'on garde une grande disponibilité car le replica etant hot, il est deja pres a prendre la place du master Redis.
#### Inconvénients
* Mise en place d'un load balancer vers le master qui redirigerai immediatement les requetes vers l'autre BD Redis.
