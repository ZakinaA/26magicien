/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.magicien.model;

/**
 *
 * @author ts1sio
 */
public class TypeEngin {
    
    private String codeTypeEngin;
    private String libelle;
    
    public TypeEngin() {
        
    }
    
    public TypeEngin(String codeTypeEngin, String libelle) {
        this.codeTypeEngin = codeTypeEngin;
        this.libelle = libelle;
    }

    public String getCodeTypeEngin() {
        return codeTypeEngin;
    }

    public void setCodeTypeEngin(String codeTypeEngin) {
        this.codeTypeEngin = codeTypeEngin;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
