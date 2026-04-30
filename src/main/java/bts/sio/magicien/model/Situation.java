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
    private Intervention intervention;
    private String libelle;
    
    public Situation(){
        
    }
    
    public Situation(int id, Intervention intervention, String libelle) {
        this.id = id;
        this.intervention = intervention;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
