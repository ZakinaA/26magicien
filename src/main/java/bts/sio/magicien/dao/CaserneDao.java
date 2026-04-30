/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bts.sio.magicien.dao;

import bts.sio.magicien.model.Caserne;
import bts.sio.magicien.model.Engin;
import bts.sio.magicien.util.ConnexionBdd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ts1sio
 */
public class CaserneDao {

    /**
     * Récupère la liste de toutes les casernes.
     */
    public List<Caserne> getToutesLesCasernes() {
        List<Caserne> liste = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM caserne ORDER BY nom";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapperCaserne(rs));
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getToutesLesCasernes : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return liste;
    }

    /**
     * Récupère une caserne par son identifiant.
     */
    public Caserne getCaserneParId(int idCaserne) {
        Caserne caserne = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM caserne WHERE idCaserne = ?";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCaserne);
            rs = ps.executeQuery();
            if (rs.next()) {
                caserne = mapperCaserne(rs);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getCaserneParId : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return caserne;
    }

    /**
     * Récupère les engins rattachés à une caserne.
     */
    public List<Engin> getEnginsDeCaserne(int idCaserne) {
        List<Engin> engins = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Les engins d'une caserne sont ceux dont l'idEngin est >= idEngin de la caserne
        // et < idEngin de la caserne suivante. Mais le schéma utilise une FK caserne->engin.
        // On récupère tous les engins référencés par cette caserne via la logique métier.
        String sql = "SELECT e.*, te.libelle AS libelleType "
                   + "FROM engin e "
                   + "INNER JOIN typeEngin te ON e.codeTypeEngin = te.codeTypeEngin "
                   + "INNER JOIN caserne c ON c.idEngin <= e.idEngin "
                   + "WHERE c.idCaserne = ? "
                   + "ORDER BY e.codeTypeEngin, e.numeroOrdre";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCaserne);
            rs = ps.executeQuery();
            while (rs.next()) {
                Engin e = new Engin();
                e.setIdEngin(rs.getInt("idEngin"));
                e.setCodeTypeEngin(rs.getString("codeTypeEngin"));
                e.setNumeroOrdre(rs.getInt("numeroOrdre"));
                engins.add(e);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getEnginsDeCaserne : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return engins;
    }

    /**
     * Ajoute une nouvelle caserne.
     */
    public int ajouterCaserne(Caserne caserne) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idGenere = -1;

        String sql = "INSERT INTO caserne (idEngin, nom, rue, codePostal, ville) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, caserne.getIdEngin());
            ps.setString(2, caserne.getNom());
            ps.setString(3, caserne.getRue());
            ps.setString(4, caserne.getCodePostal());
            ps.setString(5, caserne.getVille());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenere = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur ajouterCaserne : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return idGenere;
    }

    /**
     * Met à jour les informations d'une caserne.
     */
    public boolean modifierCaserne(Caserne caserne) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE caserne SET nom = ?, rue = ?, codePostal = ?, ville = ? "
                   + "WHERE idCaserne = ?";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, caserne.getNom());
            ps.setString(2, caserne.getRue());
            ps.setString(3, caserne.getCodePostal());
            ps.setString(4, caserne.getVille());
            ps.setInt(5, caserne.getIdCaserne());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur modifierCaserne : " + e.getMessage());
            return false;
        } finally {
            ConnexionBdd.fermer(ps);
            ConnexionBdd.fermer(con);
        }
    }

    private Caserne mapperCaserne(ResultSet rs) throws SQLException {
        Caserne c = new Caserne();
        c.setIdCaserne(rs.getInt("idCaserne"));
        c.setIdEngin(rs.getInt("idEngin"));
        c.setNom(rs.getString("nom"));
        c.setRue(rs.getString("rue"));
        c.setCodePostal(rs.getString("codePostal"));
        c.setVille(rs.getString("ville"));
        return c;
    }
    /**
 * Supprime une caserne par son identifiant.
 */
public boolean supprimerCaserne(int idCaserne) {
    Connection con = null;
    PreparedStatement ps = null;
    boolean ok = false;

    String sql = "DELETE FROM caserne WHERE idCaserne = ?";
    try {
        con = ConnexionBdd.ouvrirConnexion();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idCaserne);
        
        // executeUpdate renvoie le nombre de lignes supprimées
        int nbLignes = ps.executeUpdate();
        ok = (nbLignes > 0);
        
    } catch (SQLException e) {
        System.err.println("[SDIS] Erreur supprimerCaserne : " + e.getMessage());
    } finally {
        // On utilise ta méthode utilitaire pour bien fermer
        ConnexionBdd.fermerTout(null, ps, con);
    }
    return ok;
}
    
}