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
