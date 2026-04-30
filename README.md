# 26Magicien — Application de gestion des pompiers

Projet scolaire **BTS SIO** — Gestion des casernes et du personnel pompier pour un SDIS (Service Départemental d'Incendie et de Secours).

---

## Contexte

Application web développée dans le cadre du BTS SIO. Elle permet de gérer les casernes et les pompiers affectés à chacune d'elles, via une interface web connectée à une base de données MariaDB.

---

## Stack technique

| Couche | Technologie |
|---|---|
| Langage | Java 17+ |
| Framework web | Jakarta EE (Servlets + JSP) |
| Serveur | Apache Tomcat 10.1 |
| Base de données | MariaDB |
| Accès données | JDBC (pilote `org.mariadb.jdbc`) |
| Vues | JSP + JSTL |
| Style | CSS personnalisé |

---

## Structure du projet
26Magicien/
├── src/
│   └── bts/sio/magicien/
│       ├── dao/              # Accès base de données (CaserneDao, ...)
│       ├── model/            # Modèles métier (Caserne, Pompier)
│       ├── servlet/          # Servlets (ServletCaserne, PompierServlet, ...)
│       └── util/             # Utilitaires (ConnexionBdd)
└── web/
├── META-INF/
├── WEB-INF/
│   ├── beans.xml
│   └── web.xml           # Configuration des servlets et mappings
├── vues/
│   ├── caserne/
│   │   ├── listerCasernes.jsp
│   │   └── consulterCaserne.jsp
│   ├── pompier/
│   │   └── listePompiers.jsp
│   └── css/
│       └── style.css
└── index.jsp

---

## Base de données

- **SGBD :** MariaDB
- **Nom de la base :** `magicienpompier`
- **Hôte :** `127.0.0.1`
- **Port :** `3307`
- **Utilisateur :** `root`
- **Mot de passe :** *(vide par défaut)*

La connexion est gérée de façon centralisée dans la classe `ConnexionBdd` (`bts.sio.magicien.util`), qui expose les méthodes `ouvrirConnexion()` et `fermerTout()`.

> ⚠️ Avant de lancer le projet, vérifiez que MariaDB tourne sur le port **3307** et que la base `magicienpompier` existe.

---

## Installation et lancement

### Prérequis

- JDK 17 ou supérieur
- Apache Tomcat 10.1
- MariaDB (port 3307)
- NetBeans (recommandé) ou tout IDE compatible Jakarta EE

### Étapes

1. **Cloner ou importer le projet** dans NetBeans

2. **Créer la base de données** et importer le script SQL :
```sql
CREATE DATABASE magicienpompier;
```
Puis importer le script de création des tables fourni séparément.

3. **Vérifier la connexion** dans `ConnexionBdd.java` :
```java
private static final String URL = "jdbc:mariadb://127.0.0.1:3307/magicienpompier";
private static final String USER = "root";
private static final String PASSWORD = "";
```

4. **Déployer sur Tomcat** via NetBeans (bouton Run) ou en générant le `.war` et en le déposant dans le dossier `webapps/` de Tomcat.

5. **Accéder à l'application** :
http://localhost:8080/26magicien/
---

## Fonctionnalités

- Lister toutes les casernes
- Consulter le détail d'une caserne avec son personnel affecté
- Supprimer une caserne
- Lister tous les pompiers

---

## Mappings des servlets

| URL | Servlet | Action |
|---|---|---|
| `/ServletCaserne/listerCasernes` | `ServletCaserne` | Liste toutes les casernes |
| `/ServletCaserne/consulterCaserne?id=X` | `ServletCaserne` | Détail d'une caserne |
| `/ServletCaserne/supprimerCaserne?id=X` | `ServletCaserne` | Supprime une caserne |
| `/PompierServlet/listePompiers` | `PompierServlet` | Liste tous les pompiers |
| `/ConsulterPompier?id=X` | `ConsulterPompier` | Détail d'un pompier |

---

## Auteurs

Projet réalisé par l'équipe **Magicien** dans le cadre du **BTS SIO** composé de Ali SANGARE, Arda ALAN, Gabin HERVE, Robin BARETTE.
