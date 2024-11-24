### **TP : Création d'une API REST pour la gestion des produits**

---

### **Sujet du TP**

Vous devez développer une API REST pour gérer une collection de **produits**. L'API doit inclure les fonctionnalités suivantes :

1. **Créer un produit :**
   - Les données du produit doivent respecter les règles suivantes :
     - `id` : Obligatoire, entier unique.
     - `name` : Obligatoire, chaîne de 3 à 50 caractères.
     - `price` : Obligatoire, positif, ne doit pas dépasser **1000**.
     - `category` : Obligatoire, doit appartenir à une des catégories définies dans une **enum** (`ELECTRONICS`, `BOOKS`, `CLOTHING`).
   - Si le prix dépasse 1000, une exception métier personnalisée **`MaxPriceExceededException`** doit être levée.

2. **Modifier un produit existant :**
   - Les mêmes règles de validation que pour la création s'appliquent.
   - Si l'ID du produit n'existe pas, une erreur doit être renvoyée.

3. **Récupérer un produit par ID :**
   - Si l'ID n'existe pas, une réponse HTTP 404 avec un message personnalisé doit être renvoyée.

4. **Supprimer un produit par ID :**
   - Si l'ID n'existe pas, aucune exception ne doit être levée, mais la suppression doit être ignorée.

5. **Enumération pour `category` :**
   - Les catégories autorisées sont définies dans une enum `Category` : `ELECTRONICS`, `BOOKS`, `CLOTHING`.

6. **Gestion globale des exceptions :**
   - Gestion de l'exception `MaxPriceExceededException` avec une réponse HTTP 400.
   - Gestion de l'exception `IllegalArgumentException` pour les erreurs générales avec une réponse HTTP 400.
   - Gestion des erreurs inattendues avec une réponse HTTP 500.

---