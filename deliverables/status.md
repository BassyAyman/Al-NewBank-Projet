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