package bts.sio.magicien.test;

import bts.sio.magicien.dao.CaserneDao;
import bts.sio.magicien.model.Caserne;
import bts.sio.magicien.model.Engin;
import java.util.List;

/**
 * Classe de test pour CaserneDao
 * @author Robin
 */
public class CaserneDaoTest {

    public static void main(String[] args) {
        
        CaserneDao dao = new CaserneDao();

        // 1. TEST : Récupérer toutes les casernes
        System.out.println("--- Liste de toutes les casernes ---");
        List<Caserne> lesCasernes = dao.getToutesLesCasernes();
        for (Caserne c : lesCasernes) {
            System.out.println("ID: " + c.getIdCaserne() + " | Nom: " + c.getNom() + " (" + c.getVille() + ")");
        }

        // 2. TEST : Récupérer une caserne spécifique (ex: ID 1)
        System.out.println("\n--- Test récupération Caserne ID 1 ---");
        Caserne uneCaserne = dao.getCaserneParId(1);
        if (uneCaserne != null) {
            System.out.println("Trouvée : " + uneCaserne.getNom());
        } else {
            System.out.println("Aucune caserne trouvée avec l'ID 1.");
        }

        // 3. TEST : Récupérer les engins d'une caserne
        System.out.println("\n--- Engins de la caserne ID 1 ---");
        List<Engin> lesEngins = dao.getEnginsDeCaserne(1);
        if (lesEngins.isEmpty()) {
            System.out.println("Aucun engin rattaché.");
        } else {
            for (Engin e : lesEngins) {
                System.out.println("Engin ID: " + e.getIdEngin() + " | Type: " + e.getCodeTypeEngin());
            }
        }

        // 4. TEST : Ajouter une caserne
        System.out.println("\n--- Test Ajout Caserne ---");
        Caserne nouvelle = new Caserne();
        nouvelle.setNom("Caserne de Test");
        nouvelle.setRue("Rue des AI");
        nouvelle.setCodePostal("14000");
        nouvelle.setVille("Caen");
        nouvelle.setIdEngin(1); // On met 1 par défaut pour le lien métier

        int nouvelId = dao.ajouterCaserne(nouvelle);
        if (nouvelId != -1) {
            System.out.println("Succès ! Nouvelle caserne ajoutée avec l'ID : " + nouvelId);
        } else {
            System.out.println("Échec de l'ajout.");
        }
    }
}