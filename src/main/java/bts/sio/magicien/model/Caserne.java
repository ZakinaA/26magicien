package bts.sio.sdis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une caserne (CIS) du SDIS.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class Caserne {

    private int idCaserne;
    private String nom;
    private String rue;
    private String codePostal;
    private String ville;

    // Relations
    private int idEngin; // engin de référence (FK dans le schéma)
    
    private List<Pompier> pompiers;

    public Caserne() {
        
        this.pompiers = new ArrayList<>();
    }

    public Caserne(int idCaserne, String nom, String rue, String codePostal,
                   String ville, int idEngin) {
        this();
        this.idCaserne = idCaserne;
        this.nom = nom;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.idEngin = idEngin;
    }

    // --- Getters & Setters ---

    public int getIdCaserne() { return idCaserne; }
    public void setIdCaserne(int idCaserne) { this.idCaserne = idCaserne; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public int getIdEngin() { return idEngin; }
    public void setIdEngin(int idEngin) { this.idEngin = idEngin; }

   

    public List<Pompier> getPompiers() { return pompiers; }
    public void setPompiers(List<Pompier> pompiers) { this.pompiers = pompiers; }

    /**
     * Retourne l'adresse complète de la caserne.
     */
    public String getAdresseComplete() {
        return rue + ", " + codePostal + " " + ville;
    }

    @Override
    public String toString() {
        return "Caserne{" + idCaserne + ", " + nom + ", " + ville + "}";
    }
}
