# Choix d'implémentation du batch de mise a jour des balances

* Statut : proposé
* Date : 02/12/2023

***Histoire technique*** : Le batch a été implémenté en mettant à jour la balance de tous les comptes dans le master au Groenland, puis en
mettant à 0 le débit en cours de tous les comptes dans la base redis.

## Contexte et énoncé du problème
Le problème de faire les 2 mises à jour séquentiellement pour l'ensemble des clients est que cela peut provoquer une
incohérence dans la balance du client. En effet, si un client fait une transaction pendant que le batch est en train de
mettre à jour le master, le débit va être actualisé dans la base redis mais sera remis à 0 juste après par le batch.

## Options envisagées
### Mettre à jour redis et postgres master progressivement pour chaque client
L'idée de cette solution est que pour chaque client, le batch mette à jour le compte dans la base de données master
(actualiser la balance en fonction du débit en cours) puis dans la base de données redis. Tout en bloquant les
transactions entrantes dans cette période.
#### Avantages
* Ne bloque pas les clients pendant une période trop longue
* N'autorise aucune incohérence
#### Inconvénients
* Nécessite de mettre en place un système de verrouillage des transactions entrantes pendant la mise à jour