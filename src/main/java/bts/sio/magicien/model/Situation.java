/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.magicien.model;

/**
 *
 * @author ts1sio
 */
public class Situation {
    
    private int id;
    private int idIntervention;
    private String libelle;
    
    public Situation(){
        
    }
    
    public Situation(int id, int idIntervention, String libelle) {
        this.id = id;
        this.idIntervention = idIntervention;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdIntervention() {
        return idIntervention;
    }

    public void setIdIntervention(int idIntervention) {
        this.idIntervention = idIntervention;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
