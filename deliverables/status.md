# Weekly Status ( Week 48 ) EVO

## What was done : 
* ADR_003 : Autre moyen de faire pour la mise a jour coherente
* ADR_004 : Hot replica Redis
* Implementation nouvelle transaction impliquant Redis
* Implementation UpdateBatchingService
* Test meme scenario de payement que pour la demo de construction avec test de charge pour la nouvelle version

## What is planned 
* test scenario de payement enchainer par un meme client
* Mise en place d'un replica de redis
* Explorer une soltution impliquant docker-swarm.

## What's the difference from last week
* Nouveaux ADR
* CI plus rapide, ajuster
* Une instance du Web Service pouvant etre multiplier dynamiquement au moment du docker-compose up. (plus d'instance static)

## Issues : 
* Question qui se pose, quelle est la meilleurs approche concernant l'update generale du systeme de transaction. Il est obligatoire de maintenir une coherence forte.
Cela pose alors une grande problematique sur la disponibilité de la possibilité de payer sur le Web sur les horaires local a la region de faible trafique choisie pour
remettre les compteurs a zero. ( solution exploré - ADR_003).

## Risk : 
* none

## RYG Flag : 
* Green : Nous avons globalement bien avancer au niveau de la reflexion et de l'implementation. Nous avons un walking skeleton de notre process de transaction.
  Nous avons aussi une premiere version "naive" implementer en typescript du batching process. Et nous avons pu discuter et mener une reflexion general sur les choix possible
  a faire afin de d'optimiser la question de disponibilité posée. Ils nous restera pour la suite a explorer les soltitions envisageable au niveau du deployement sur plusieurs
  region (unique si plus de 30m de personne), la mise en place de solution de fails-over inter-region possible et enfin la mise en place de regle propre a chaque region.


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
