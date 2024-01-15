# auto-evaluation d'equipe 

## Repartition des points : 
* Ayman : 100 pts
* Igor : 100 pts
* Mathieu : 100 pts
* Tobias : 100 pts

Les membres de l'equipe ce sont tous inversit de maniere equivalente dans le projet, sur les differents aspect de celui .

# Weekly Status ( Week 50 ) EVO

## What was done : 
* ADR_006 : BrainStorm pour savoir comment changer nos données pour qu'elle match un modele qui pourrai etre plus favorable a l'implementation du fail-over
* Implementation du changement de modele de donnée de debit dans REDIS
* Reflexion sur le fail-over

## What is planned 
* Implementation et scenario avec le nouveau modele de donnée, que sa casse rien
* Voir pour la demo du fail over si on peut faire deux reseau differente avec docker compose, sinon on utilisera kubernetes avec mise en place de deux namespace differents pour la demo
  (a voir aussi lequel sera le plus simple a faire)

## What's the difference from last week
* La nouvel implementation

## Issues : 
* None

## Risk : 
* A propos de la demo, Est-t-il vraiment necessaire de faire deux reseaux vraiment differents pour demontrer deux regions vraiment separé, pour faire la demo du failover.
  Possibilité ou non de rester credible dans la demo meme si les deux "regions" sont dans le meme reseau (c'est a dire que la connection inter region peut se faire directement sur le plan
  des conteneurs, et non pas via rest ou gPRC. Simplification du deploiement de la solution pour la demo).

## RYG Flag : 
* Green : Integration de la nouvel implementation. Nous somme en train de grandement anticipé ce que nous allons faire a propos du failover pour garder la meme direction, meme apres 2 semaine de vacance de noel. Nous nous somme rendu compte que notre problematique de failOver a soulever un problemee lier a nos donnée, en effet celle-ci on été faite dans le cadre de l'implementation d'une region, pendant la periode d'AL construction. Mais actuellement, pour faire communiquer des regions entre elles, nous nous somme rendu compte qu'au lieu d'essayer de mettre en place l'impossible au dessus de notre code, peut etre que revoir les données transitant durant le process de transaction et le retravailler allait nous permettre de mieux concevoir une solution a notre problematique de fails Overs.

# Weekly Status ( Week 49 ) EVO

## What was done : 
* ADR_005 : Solution au probleme de coherence du debis en cas de transaction client pendant un batch process
* Reflexion sur une implementation lier au fails-overs inter-regions
* Test du scenario du client qui effectue plusieurs transaction d'affiler

## What is planned 
* Docker Swarm implementation apres disution avec l'equipe mardi
* Changement dans l'implementation general pour faire matcher l'ADR_005 apres discrution avec l'equipe mardi
* Mise en attente de la mise en place du replica Redis. (utilité immediate moindre, risque de se faire refactor par la suite)

## What's the difference from last week
* Nouveaux ADR
* Ajustement dans la CI/CD
* Une instance du Web Service pouvant être multipliée dynamiquement lors du `docker-compose up` (fin des instances statiques)

## Issues : 
* Deploiement avec docker-compose problematique pour des besoins de realisation de demo. Voir pour la mise en place d'une autre solution permettant la mise en place
  du fail-over inter regions basée sur le DNS pour rediriger les requetes.

## Risk : 
* Mauvaise interpretation d'un fails-over inter regions. Nous ne savons pas si il s'agit d'un fails over d'un service, ce qui ve rediriger la requete vers le meme service dans une autre regions
  ou si c'est un fails overs si toute l'infrastructure tombe.

## RYG Flag : 
* Green : Nous avons fini de refactor le flow de transaction et de batch process client par client. Celui-ci est nettement moins rapide a realiser ( a peu pres 1m15s ). Mais se realise pour 2500
  client (soit 30 ms par client). Precedament cela prenais 5 seconde environs, ce qui bloquer tout les client pendant 5 secondes.
  Premiere recherche sur la comprehension du troisieme point lier au failOver inter region effectuer. L'idée et de savoir comment connecter les regions entres elles, pour repondre au besoin
  immediat de fails-over, et plus tard une logique d'administration par region de regle.

# Weekly Status ( Week 48 ) EVO

## What was done : 
* ADR_003 : Autre moyen de mise à jour cohérente
* ADR_004 : Hot replica Redis
* Implémentation d'une nouvelle transaction impliquant Redis
* Implémentation du service UpdateBatchingService
* Test du même scénario de paiement que pour la démo de construction avec test de charge pour la nouvelle version

## What is planned 
* Test de scénarios de paiement enchaînés par un même client
* Mise en place d'un réplica de Redis
* Exploration d'une solution impliquant Docker Swarm

## What's the difference from last week
* Nouveaux ADR
* CI plus rapide, ajustement effectué
* Une instance du Web Service pouvant être multipliée dynamiquement lors du `docker-compose up` (fin des instances statiques)

## Issues : 
* Question en suspens : quelle est la meilleure approche concernant la mise à jour générale du système de transaction ? Il est crucial de maintenir une cohérence forte.
  Ceci pose alors un grand défi quant à la disponibilité de la possibilité de paiement sur le Web durant les heures creuses locales choisies pour remettre les compteurs à zéro. (solution explorée - ADR_003).

## Risk : 
* Aucun

## RYG Flag : 
* Green : Nous avons globalement bien avancé en termes de réflexion et d'implémentation. Nous disposons d'un squelette fonctionnel de notre processus de transaction.
  Nous avons également une première version "naïve" implémentée en TypeScript du processus de batch. De plus, nous avons pu discuter et réfléchir globalement sur les choix possibles afin d'optimiser la question de disponibilité.
  Il nous reste à explorer les solutions envisageables en termes de déploiement sur plusieurs régions (unique si plus de 30 millions de personnes), la mise en place de solutions de basculement inter-régions et enfin l'établissement de règles propres à chaque région.


# Weekly Status ( Week 47 ) EVO

## What was done : 
* Les ADR 001 et 002 pour respactivement les changements lier au deux premier points a modifier dans l'archi
* refactor of update service
* Peuplement correct de la DB
* Mise en place REDIS sur le WebServiceTransaction

## What is planned 
* Reproduire les memes scenarios de payement avec les nouvelles implementation
* Mettre en place le batch et tester

## What's the difference from last week
* le scenario suivi (cf doc d'archi)
* l'architecture (cf doc d'archi)

## Issues : 
* Quelque petit soucis avec le load balancer, creation de multiple instance "static" dans le docker compose
* Question qui se pose, nous pensons avoir atteint les limites d'orchestration possible avec docker-compose et envisageons de peut etre changer

## Risk : 
* Durant le process de mise a jour coherente de notre service batch, nous somme en consistance eventuel. Soit nous reduisont la disponibilité du service en lockant la possibilité de faire un transaction, ou bien nous faisons un lock plus local

## RYG Flag : 
* Green : Nous avons rapidement reussi a trouver une solution a notre soucis de coherence. En effet nous devons emuler un systeme fortement coherent et tres rapide avec des ressources materiel tres limité. En effet dans des cas concret, les outils mis en oeuvre sont assez consequent. Nous avons commencer a implementer la chose, et sur cette semaine cela devrai etre fini et pres a etre tester dans un scenario de charge pour voir les resultats de notre architecture, afin de pouvoir la pousser a bout et de passer au second grand test qu'est la resilience. Une fois le process reussi et confirmer, nous allons passer a notre 3eme points lier au deploiement.
