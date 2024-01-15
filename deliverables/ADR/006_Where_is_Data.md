# FailOver inter region topic : Question sur Ou mettre finalement les données client ?

* Statut : proposé - A voir
* Date : 17/12/2023

***Histoire technique*** : Nous devons realiser un mecanisme de failOver inter region, dans lequel notre solution de banque serai deployer. Plusieurs discution on été effectuer a cette egare,
mais une question principale subsiste. En effet, quelque soit la solution proposé, le placement de nos donnée entre les regions, ou dans les regions reviens souvent. Ainsi avant 
de nous fixé une solution au niveau du failOver, il faut qu'on decide de comment seront gerer les données de plusieurs client dans des regions differentes.

## Contexte et énoncé du problème
Le mechanisme de fail-over inter region et tres fortement lier a une autre feature qui est le payement d'un client dans une region qui n'est pas la sienne. La question principale qui se 
pose dans les deux cas, est la suivante : Le client en question n'apartient pas a la region, donc un nouveau debit lui sera crée dans REDIS, mais ce meme client, n'est pas present dans la
Base de donnée postgres master de la region qui lui est etrangere. Autre chose, si il avait deja un debit en cours dans une autre region, alors la region etrangere n'est pas au courant et cela
pourrai resulter sur un probleme de payement rendu possible alors que celui-ci n'a pas les fonds. Deux solutions on été penser, soit accepter la possibilité qu'il soit dans le negatif, sinon
reussir a recuperer ces dernieres donnée dans la base de donnée de sa region. Mais dans le cas d'un failover, celle-ci seraient inccessible.
Finalement, afin de reduire la complexité dans la recherche de cette solution, il a été decider de d'abord revoir comment les données des clients serai organiser, sachant que l'on aurai plusieurs
regions. Et de refactorer notre code en concequence.

## Solution existante
Notre modele correspond bien a ce qui pourrai etre dans une seul region, mais dire que nous allons simplement dupliquer la solution d'une region en plusieurs regions les rend en un sens
independante les une des autres, avec leurs propre modele de donnée. Actuellement, nous avons une base de donnée Master lier a un slave dans un modele CQRS. Le Slave est dans la regions
et le master et purement lier au slave dans sa totalité, et est present au groenland.

## Options envisagées
### Maintenir la chose et juste ajouter au niveau autant de base de donnée different au groenland qu'il y aura de regions (potentiellement changement du modele de base de donnée vers noSQL)
IMAGE..
#### Avantages
* Moins de changement a faire dans le code
* Dans un contexte de large scale deploiement, tres interesant
* Permet d'eviter un scaling vertical indecent
#### Inconvénients
* Donnée de client de region differente sur des base de donnée differente, solution nul en SQL, tres interessante en noSQL
* Jointure tres complexe a faire => Service intermediaire pour faire les ecritures de nouveau client dans une nouvel regions (perte de temps)

### Changer la structure de nos donnée dans la base de donnnée
IMAGE..Deploiment
Mise en place d'une table par region qui pourrai permettre de recuperer des données de clients sur d'autre table deja exstante derriere via des jointures
Liaison ONEtoONE a chaque fois pour lier un client a une regions a chaque fois.
### Avantages
* Exploitation des avantages offert par SQL.
* Solution de failOver de by pass de redis pour aller chercher le debit directement dans la DB Master commune au region
### Inconvenients
* Plusieurs point d'entre vers la base de donnée (forte inspiration de la pres de clement MyCoachPro)
* Ecriture dans la DB plus lente
* SPOF
