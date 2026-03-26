package bts.sio.magicien.model;

/**
 * Classe représentant le grade d'un pompier.
 * Ex: Capitaine, Lieutenant, Sergent-chef, Sapeur 1re classe, etc.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class Grade {

    private int idGrade;
    private int idPompier;
    private String libelle;

    public Grade() {}

    public Grade(int idGrade, int idPompier, String libelle) {
        this.idGrade = idGrade;
        this.idPompier = idPompier;
        this.libelle = libelle;
    }

    public int getIdGrade() { return idGrade; }
    public void setIdGrade(int idGrade) { this.idGrade = idGrade; }

    public int getIdPompier() { return idPompier; }
    public void setIdPompier(int idPompier) { this.idPompier = idPompier; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    @Override
    public String toString() {
        return "Grade{" + idGrade + ", " + libelle + "}";
    }
}
