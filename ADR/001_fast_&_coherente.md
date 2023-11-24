# Mise en place d'une architecture de transaction rapide et coherente en reponse du retrait de l'hypothese sur le WebServiceValidation

* Statut : proposé et accepté, en cours de developpement pour confirmation
* Décideurs : Bassy Ayman.
* Date : 24/11/2023

***Histoire technique*** : Afin de maintenir notre systeme coherent, nous avions formuler une hypothese selon laquel un 
client ne pourrai pas faire de nouvel achat dans les 5 minutes qui suivent un achat precedent. De cette maniere le
processus asynchrone de mise a jour du compte aurai largement le temps d'update les dernieres données du compte client.

## Contexte et énoncé du problème
Dans un contexte ou le WebServiceValidation traite des demandes venant d'internet, il existe des bots, ou autres, qui
formule un a deux voir trois achat en l'espace de 5 minutes. Dans certains cas il peuvent en faire un tres grand nombre
dans ce meme laps de temps. Ce qui fait que notre hypothese n'est plus valide, et donc cela resultera d'une incoherence
des données du compte client, et donc plus de permissibilité.
Ce qui est un grand probleme dans le contexte de transaction d'une **Banque**.

## Facteurs de décision
* Incertitude sur la rapidité de mise a jour du compte client suivant le processus asynchrone par rapport a celle
du client effectuant sont second achat.
* Dependance vis a vis du service d'update pour permettre au systeme de pouvoir a nouveau traiter une transaction pour
un meme client. Temps d'incoherence probable plus long en cas de panne du service d'update.

## Options envisagées
* Ne plus dependre de la finition du processus asynchrone de mise a jour du compte client pour pouvoir retomber sur un etat
pouvant rendre possible la realisation d'une transaction pour le meme client.
* Ne plus modifier le compte client directement apres une transaction, mais plutot un autre indicateur de transaction
appelé debit.
* Avoir un batch service automatisé qui va se charger de verrouillé l'action de transaction pour un client, et va 
mettre a jour le compte client en fonction du debit accumulé. Batch trigger par un impossibilité du client de faire un 
achat pour cause de debit trop elevé resultant d'un transaction > argent compte - debit. Ou d'un timer toute les x secondes
* Mise en place d'un cache au niveau du service de validation de transaction pour garder de maniere tres rapidement accessible
les debits de chaque client. Et donc recuperer de la BD slave simplement l'argent du compte client. Le réel argent disponible
sera ensuite calculé par le service pour validé ou non la transaction. De maniere asynchrone, la transaction servira a 
incrementer le debit du client en BD, pour maintenir une coherence des données.

## Avantages et inconvénients de cette solution

###  Avantages
* Maintient d'une forte coherence durant le processus de transaction. Meme si le client etait amener a faire une
  multitude de transaction en un temps tres court, ne basant pas l'historique des transaction payé sur une modification sur
  le compte client mais sur un debit rapidement accessible par le service, mis a jour immediatement apres la transaction dans le master,
  le client pourra toujours faire une transaction.
* Ainsi nous maintenons une grande disponibilité du payement pour le client. Tout en gardant un process s'executant
  rapidement. Ce qui est le plus grand enjeu de notre systeme.
* Nous maintenons aussi une forte coherence des données, et durant le laps de seconde de mise a jour coherente effectuer
  par le batch, les données etant verrouillé, il n'y aura pas de probleme de coherence.

### inconvenients
* Dans le cadre d'une forte coherence comme enoncé, la laps de temps du batching process sera donc fait tous en etant
  en etat de verrou, ce qui signifie qu'ou aucune transaction ne pourra etre faite (renvoie d'erreur), ou alors celle-ci engager
  durant ce laps de temps sera mis en attente, augmentant ainsi le temps de traitement de la transaction.
  Il est toute fois bon de noté que le process pourra etre effectuer globalement sur l'ensemble des clients a un moment ou ceux-ci
  sont le moins actif, comme par exemple la nuit.
* Le cache joue un role primordiale dans la coherence des données de debit. En effet meme si celle-ci sont repliquer de 
maniere asynchrone vers le master, on se place dans le laps de temps ou elle ne le sont pas encore, sur ce laps de temps
notre source de verité sur le montant de debit et dans le cache. Or si le cache venait a subir une panne, ce serai un grave probleme.