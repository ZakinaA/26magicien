package bts.sio.sdis.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une situation (type d'incident) référencée.
 * Ex: Feu dans un appartement, accident de la route, ascenseur bloqué, etc.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class Situation {

    private int idSituation;
    private int idIntervention;
    private String libelle;

    // Relations : types d'engins nécessités par cette situation
    private List<TypeEngin> typesEnginsNecessaires;

    public Situation() {
        this.typesEnginsNecessaires = new ArrayList<>();
    }

    public Situation(int idSituation, int idIntervention, String libelle) {
        this();
        this.idSituation = idSituation;
        this.idIntervention = idIntervention;
        this.libelle = libelle;
    }

    public int getIdSituation() { return idSituation; }
    public void setIdSituation(int idSituation) { this.idSituation = idSituation; }

    public int getIdIntervention() { return idIntervention; }
    public void setIdIntervention(int idIntervention) { this.idIntervention = idIntervention; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    public List<TypeEngin> getTypesEnginsNecessaires() { return typesEnginsNecessaires; }
    public void setTypesEnginsNecessaires(List<TypeEngin> typesEnginsNecessaires) {
        this.typesEnginsNecessaires = typesEnginsNecessaires;
    }

    @Override
    public String toString() {
        return "Situation{" + idSituation + ", " + libelle + "}";
    }
}
