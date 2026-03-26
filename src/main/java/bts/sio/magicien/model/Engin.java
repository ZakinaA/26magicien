package bts.sio.sdis.model;

/**
 * Classe représentant un engin de secours (FPT, VSAV, EPA, etc.).
 * Chaque engin est d'un type particulier et dépend d'une caserne.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class Engin {

    private int idEngin;
    private String codeTypeEngin;
    private int numeroOrdre;

    // Relations
    private TypeEngin typeEngin;

    public Engin() {}

    public Engin(int idEngin, String codeTypeEngin, int numeroOrdre) {
        this.idEngin = idEngin;
        this.codeTypeEngin = codeTypeEngin;
        this.numeroOrdre = numeroOrdre;
    }

    // --- Getters & Setters ---

    public int getIdEngin() { return idEngin; }
    public void setIdEngin(int idEngin) { this.idEngin = idEngin; }

    public String getCodeTypeEngin() { return codeTypeEngin; }
    public void setCodeTypeEngin(String codeTypeEngin) { this.codeTypeEngin = codeTypeEngin; }

    public int getNumeroOrdre() { return numeroOrdre; }
    public void setNumeroOrdre(int numeroOrdre) { this.numeroOrdre = numeroOrdre; }

    public TypeEngin getTypeEngin() { return typeEngin; }
    public void setTypeEngin(TypeEngin typeEngin) { this.typeEngin = typeEngin; }

    /**
     * Retourne le libellé court de l'engin (ex: FPT01, VSAV02).
     */
    public String getLibelleCourt() {
        return codeTypeEngin + String.format("%02d", numeroOrdre);
    }

    @Override
    public String toString() {
        return "Engin{" + idEngin + ", " + getLibelleCourt() + "}";
    }
}
