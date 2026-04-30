/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.magicien.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un pompier du SDIS.
 * Un pompier peut être PROFESSIONNEL ou VOLONTAIRE.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class Pompier {

    private int idPompier;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numeroBip;
    private String type; // PROFESSIONNEL ou VOLONTAIRE
    private String motDePasse;
    private String statut;

    // Relations
    private int idCaserne;
    private Caserne caserne;
    private Grade grade;
    private List<Fonction> fonctions;

    public Pompier() {
        this.fonctions = new ArrayList<>();
    }

    public Pompier(int idPompier, String nom, String prenom, LocalDate dateNaissance,
                   String numeroBip, String type, String motDePasse, String statut, int idCaserne) {
        this();
        this.idPompier = idPompier;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroBip = numeroBip;
        this.type = type;
        this.motDePasse = motDePasse;
        this.statut = statut;
        this.idCaserne = idCaserne;
    }

    // --- Getters & Setters ---

    public int getIdPompier() { return idPompier; }
    public void setIdPompier(int idPompier) { this.idPompier = idPompier; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getNumeroBip() { return numeroBip; }
    public void setNumeroBip(String numeroBip) { this.numeroBip = numeroBip; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public int getIdCaserne() { return idCaserne; }
    public void setIdCaserne(int idCaserne) { this.idCaserne = idCaserne; }

    public Caserne getCaserne() { return caserne; }
    public void setCaserne(Caserne caserne) { this.caserne = caserne; }

    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }

    public List<Fonction> getFonctions() { return fonctions; }
    public void setFonctions(List<Fonction> fonctions) { this.fonctions = fonctions; }

    /**
     * Retourne le nom complet du pompier (Prénom Nom).
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }

    @Override
    public String toString() {
        return "Pompier{" + idPompier + ", " + getNomComplet() + ", " + type + "}";
    }

    public void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}