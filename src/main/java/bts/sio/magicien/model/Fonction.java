/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.magicien.model;

/**
 * Classe représentant une fonction/habilitation d'un pompier.
 * Ex: Chef agrès FPT, Conducteur VSR, Equipier incendie, etc.
 *
 * @author Equipe Magicien
 * @version 1.0
 * @since 2025-03-15
 */
public class Fonction {

    private String codeFonction;
    private String libelle;

    public Fonction() {}

    public Fonction(String codeFonction, String libelle) {
        this.codeFonction = codeFonction;
        this.libelle = libelle;
    }

    public String getCodeFonction() { return codeFonction; }
    public void setCodeFonction(String codeFonction) { this.codeFonction = codeFonction; }

    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }

    @Override
    public String toString() {
        return "Fonction{" + codeFonction + ", " + libelle + "}";
    }
}