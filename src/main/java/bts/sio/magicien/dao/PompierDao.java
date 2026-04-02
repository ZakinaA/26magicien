package bts.sio.magicien.dao;

import bts.sio.magicien.model.*;
import bts.sio.magicien.util.ConnexionBdd;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * DAO pour l'accès aux données de la table pompier.
 * Fournit les opérations CRUD et les requêtes métier associées.
 *
 * @author Equipe Magicien
 * @version 1.0
 * @since 2025-03-15
 */
public class PompierDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Pompier> getTousLesPompiers() {
        List<Pompier> liste = new ArrayList<>();
        
        String sql = "SELECT p.*, c.nom AS nomCaserne, c.ville AS villeCaserne, "
                   + "g.idGrade, g.libelle AS libelleGrade "
                   + "FROM pompier p "
                   + "LEFT JOIN caserne c ON p.idCaserne = c.idCaserne "
                   + "LEFT JOIN grade g ON p.idPompier = g.idPompier "
                   + "ORDER BY p.nom, p.prenom";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pompier p = mapperPompier(rs);

                // Caserne simplifiée
                Caserne caserne = new Caserne();
                caserne.setIdCaserne(rs.getInt("idCaserne"));
                caserne.setNom(rs.getString("nomCaserne"));
                caserne.setVille(rs.getString("villeCaserne"));
                p.setCaserne(caserne);

                // Grade
                int idGrade = rs.getInt("idGrade");
                if (!rs.wasNull()) {
                    Grade grade = new Grade(idGrade, p.getIdPompier(), rs.getString("libelleGrade"));
                    p.setGrade(grade);
                }

                liste.add(p);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getTousLesPompiers : " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return liste;
    }
    
     /**
     * Mappe une ligne ResultSet vers un objet Pompier.
     */
    private Pompier mapperPompier(ResultSet rs) throws SQLException {
        Pompier p = new Pompier();
        p.setIdPompier(rs.getInt("idPompier"));
        p.setNom(rs.getString("nom"));
        p.setPrenom(rs.getString("prenom"));

        Date dateNaissance = rs.getDate("dateNaissance");
        if (dateNaissance != null) {
            p.setDateNaissance(dateNaissance.toLocalDate());
        }

        p.setNumeroBip(rs.getString("numeroBip"));
        p.setType(rs.getString("type"));
        p.setMotDePasse(rs.getString("motDePasse"));
        p.setStatut(rs.getString("statut"));
        p.setIdCaserne(rs.getInt("idCaserne"));
        return p;
    }
}
