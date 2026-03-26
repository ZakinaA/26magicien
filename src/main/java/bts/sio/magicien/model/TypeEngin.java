package bts.sio.magicien.model;

/**
 * Classe représentant un type d'engin (FPT, VSAV, EPA, VSR, VLHR).
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class TypeEngin {

    private String codeTypeEngin;
    private String libelle;

    public TypeEngin() {}

    public TypeEngin(String codeTypeEngin, String libelle) {
        this.codeTypeEngin = codeTypeEngin;
        this.libelle = libelle;
    }

    public String getCodeTypeEngin() { return codeTypeEngin; }
    public void setCodeTypeEngin(String codeTypeEngin) { this.codeTypeEngin = codeTypeEngin; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    @Override
    public String toString() {
        return "TypeEngin{" + codeTypeEngin + ", " + libelle + "}";
    }
}
