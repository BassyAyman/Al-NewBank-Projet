# Changement du format de la donnée de debit et du formatage general de la donnée lier au maintient du debit

* Statut : proposé 
* Date : 09/12/2023

***Histoire technique*** : Redis utilisé pour stocker les debits clients dans une structure de type clée valeur. Cepandant, le fait que la valeur soit un integer pose un problme nouveau,
En effet si un process souaite recuperer sa valeur puis le supprimer et au autre l'incrementer d'une valeur, il pourrai l'incrementer et etre supprimer, posant un probleme critique bien plus
problematique qu'a l'origine.

## Contexte et énoncé du problème
La solution utilisant redis pour stocker le debit fix d'un client etait une solution viable afin de nous permettre de nous rendre le plus disponible possible
aupres du client. Cepandant, en vu de la mise en place du systeme de batching afin de reinisialiser l'etat de notre systeme, un soucis de coherence a été revelé. En effet, comme enoncé plus tot,
la structure de donnée utiliser impliquer que si plusieurs action se chevoche, cela crée un erreur tres critique pour le client. Celle-ci a un tres peu probable probabilité de survenir mais
peu tout de meme survenir. 
En effet, lors du batching process, celui-ci dois remettre a 0 l'argent dans le debit redis et le montant du compte a sont taux initial moins la valeur du debit et la valeur du debit a 0.
Suite a l'ADR_003, a été decider que ce process serai changer vers un autre qui est de faire le batching client par client afin de rendre le payement plus disponible du point de vu du client.
Cette nouvel maniere de faire pose un soucis potentiel, si un client fait une transaction de 10e au moment ou ses donnée sont en etat de mise a jour, il se peut que sont debit soit mis a jour au niveau
de redis, puis remis a 0. L'incohenrence est que si il avait un debit de 10 et 50 dans sont compte, a la suite du bacthing il aurai un debit de 0 et 40e dans le compte. Mais en l'occurence, il 
aurai 0 dans le debit, 40 dans sont compte ***MAIS*** 10 de debit dans la BD postgres, car le process d'update du master est asynchrone. et qu'en parallele l'on pourrai avoir l'update et le batch 
en meme temps vers le master, en fonction de celui qui passe en premier, l'on pourrai avoir un probleme.

## Scenario de mise en place du probleme
BatchingProcess ---> Fait le batch sur le ClientX dans Master (argent - 50e, debit en cours 30e) -> Fin (argent - 20e, debit - 0e)
ClientX ---> Transaction 10e -> Validation -> Mise a jour dans le debit de 10e soit un total sur la journée de 40e de debit dans redis -> Updating en cours vers master
BatchingProcess ---> debit ClientX a 0 dans Redis -> Fin batch clientX
ClientX ---> update de debit dans master fait --> (argent - 20e, debit - 10e)  ***INCOHERENCE***  REDIS(0e) et Master(10e) sur le debit.


## Options envisagées
### Chnanger la structure de donnée d'un Debit
Le debit ne serai plus un integer fix, mais une liste de debit ayant occurer a des moments X distinct.
Changement dans le flow du batching -> recuperation du nombre de transaction lier au client, ou a la date d'update en cours, et recupere les debit de la stucture afin de les supprimer
#### Avantages
* Permet de maintenir toujour la meme logique dans le flow d'une trasnaction
* Permet a un client de toujours faire un transaction et de l'ajouter et de recuperer le total des debits en cours.
#### Inconvénients
* L'acces a redis est plus lent, ajout d'une couche logique traitant la donnée de redis. Transaction generale plus lente a traiter.

### mettre en place une logique de verroue
On garde un int pour rester rapide dans la realisation d'une transaction, dans le cas du scenario, on bloque la transaction du client en faisant un timeout retry de 1 seconde en cas d'update 
pour le client.
### Avantages
* Permet de rester rapide dans le contexte d'une transaction du client
### Inconvenients
* Augemente grandement le temps mis par une transaction d'un client, en effet si un batching et en cours, un retry apres 1s et lancer tant que sont status et toujour en mode update.
