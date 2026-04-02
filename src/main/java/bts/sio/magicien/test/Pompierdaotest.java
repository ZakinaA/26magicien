/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.magicien.test;

/**
 *
 * @author ts1sio
 */
import bts.sio.magicien.dao.*;
import bts.sio.magicien.model.*;
import bts.sio.magicien.util.ConnexionBdd;
import java.sql.*;
import java.util.List;

public class Pompierdaotest {

    public static void main(String args[]) {

        PompierDao daoPompier = new PompierDao();

        List<Pompier> lesPompiers = daoPompier.getTousLesPompiers();

        System.out.println("Nombre de pompiers retournés = " + lesPompiers.size());

        for (Pompier p : lesPompiers) {
            System.out.println("* " + p.getIdPompier()
                    + " - " + p.getNom()
                    + " " + p.getPrenom()
                    + " | Caserne : " + (p.getCaserne() != null ? p.getCaserne().getNom() : "N/A")
                    + " | Grade : " + (p.getGrade() != null ? p.getGrade().getLibelle() : "N/A"));
        }
    }
}