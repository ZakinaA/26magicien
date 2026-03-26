package bts.sio.sdis.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant une intervention du SDIS.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class Intervention {

    private int idIntervention;
    private String lieu;
    private LocalDateTime dateHeureAppel;
    private LocalDateTime heureArrivee;
    private int dureeMinutes;

    // Relations
    private Situation situation;
    private List<Engin> enginsMobilises;
    private List<Pompier> pompiersAppeles;

    public Intervention() {
        this.enginsMobilises = new ArrayList<>();
        this.pompiersAppeles = new ArrayList<>();
    }

    public Intervention(int idIntervention, String lieu, LocalDateTime dateHeureAppel,
                        LocalDateTime heureArrivee, int dureeMinutes) {
        this();
        this.idIntervention = idIntervention;
        this.lieu = lieu;
        this.dateHeureAppel = dateHeureAppel;
        this.heureArrivee = heureArrivee;
        this.dureeMinutes = dureeMinutes;
    }

    // --- Getters & Setters ---

    public int getIdIntervention() { return idIntervention; }
    public void setIdIntervention(int idIntervention) { this.idIntervention = idIntervention; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public LocalDateTime getDateHeureAppel() { return dateHeureAppel; }
    public void setDateHeureAppel(LocalDateTime dateHeureAppel) { this.dateHeureAppel = dateHeureAppel; }

    public LocalDateTime getHeureArrivee() { return heureArrivee; }
    public void setHeureArrivee(LocalDateTime heureArrivee) { this.heureArrivee = heureArrivee; }

    public int getDureeMinutes() { return dureeMinutes; }
    public void setDureeMinutes(int dureeMinutes) { this.dureeMinutes = dureeMinutes; }

    public Situation getSituation() { return situation; }
    public void setSituation(Situation situation) { this.situation = situation; }

    public List<Engin> getEnginsMobilises() { return enginsMobilises; }
    public void setEnginsMobilises(List<Engin> enginsMobilises) { this.enginsMobilises = enginsMobilises; }

    public List<Pompier> getPompiersAppeles() { return pompiersAppeles; }
    public void setPompiersAppeles(List<Pompier> pompiersAppeles) { this.pompiersAppeles = pompiersAppeles; }

    /**
     * Calcule le délai de réponse en minutes entre l'appel et l'arrivée.
     */
    public long getDelaiReponseMinutes() {
        if (dateHeureAppel != null && heureArrivee != null) {
            return java.time.Duration.between(dateHeureAppel, heureArrivee).toMinutes();
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Intervention{" + idIntervention + ", " + lieu + ", " + dateHeureAppel + "}";
    }
}
