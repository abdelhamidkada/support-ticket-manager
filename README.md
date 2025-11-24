# Gestionnaire de tickets de support (Java)

Application console Java permettant de gérer des tickets de support client  
(inspirée de mon expérience d'affectation fibre chez Bell/Cnexia).

## Fonctionnalités

- Création d’un ticket (nom du client, téléphone, type, priorité, description)
- Liste de tous les tickets existants
- Recherche d’un ticket par ID
- Modification du statut  
  (`OPEN`, `IN_PROGRESS`, `RESOLVED`, `CLOSED`)
- Modification de la priorité  
  (`LOW`, `MEDIUM`, `HIGH`, `CRITICAL`)
- Filtrage des tickets par :
    - statut
    - type (`INTERNET`, `TELEPHONE`, `TV`, `FIBER`, `OTHER`)
- Sauvegarde et chargement des tickets dans un fichier `tickets.csv`

## Technologies

- Java (Programmation Orientée Objet)
- Collections (`Map`, `List`)
- Persistance simple via fichier texte (CSV)

## Exécution

Depuis un IDE (IntelliJ IDEA par exemple) :

1. Importer le projet Maven.
2. Lancer la classe suivante :

```text
ma.abdelhamid.supporttickets.Main
