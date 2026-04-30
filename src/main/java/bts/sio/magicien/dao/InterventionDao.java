package bts.sio.magicien.dao;

import bts.sio.magicien.model.*;
import bts.sio.magicien.util.ConnexionBdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour l'accès aux données des interventions.
 *
 * @author Equipe SDIS
 * @version 1.0
 * @since 2025-03-15
 */
public class InterventionDao {

    /**
     * Récupère la liste de toutes les interventions avec leur situation.
     */
    public List<Intervention> getToutesLesInterventions() {
        List<Intervention> liste = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT i.*, s.idSituation, s.libelle AS libelleSituation "
                   + "FROM intervention i "
                   + "LEFT JOIN situation s ON i.idIntervention = s.idIntervention "
                   + "ORDER BY i.dateHeureAppel DESC";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Intervention inter = mapperIntervention(rs);

                int idSit = rs.getInt("idSituation");
                if (!rs.wasNull()) {
                    Situation sit = new Situation(idSit, inter.getIdIntervention(),
                                                  rs.getString("libelleSituation"));
                    inter.setSituation(sit);
                }
                liste.add(inter);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getToutesLesInterventions : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return liste;
    }

    /**
     * Récupère une intervention par ID avec tous ses détails.
     */
    public Intervention getInterventionParId(int idIntervention) {
        Intervention inter = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT i.*, s.idSituation, s.libelle AS libelleSituation "
                   + "FROM intervention i "
                   + "LEFT JOIN situation s ON i.idIntervention = s.idIntervention "
                   + "WHERE i.idIntervention = ?";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idIntervention);
            rs = ps.executeQuery();

            if (rs.next()) {
                inter = mapperIntervention(rs);

                int idSit = rs.getInt("idSituation");
                if (!rs.wasNull()) {
                    Situation sit = new Situation(idSit, idIntervention,
                                                  rs.getString("libelleSituation"));
                    inter.setSituation(sit);
                }

                // Charger les engins mobilisés
                inter.setEnginsMobilises(getEnginsMobilises(idIntervention));

                // Charger les pompiers appelés
                inter.setPompiersAppeles(getPompiersAppeles(idIntervention));
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getInterventionParId : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return inter;
    }

    /**
     * Récupère les engins mobilisés pour une intervention.
     */
    public List<Engin> getEnginsMobilises(int idIntervention) {
        List<Engin> engins = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT e.*, te.libelle AS libelleType "
                   + "FROM mobiliser m "
                   + "INNER JOIN engin e ON m.idEngin = e.idEngin "
                   + "INNER JOIN typeEngin te ON e.codeTypeEngin = te.codeTypeEngin "
                   + "WHERE m.idIntervention = ?";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idIntervention);
            rs = ps.executeQuery();
            while (rs.next()) {
                Engin e = new Engin();
                e.setIdEngin(rs.getInt("idEngin"));
                e.setCodeTypeEngin(rs.getString("codeTypeEngin"));
                e.setNumeroOrdre(rs.getInt("numeroOrdre"));

                TypeEngin te = new TypeEngin(rs.getString("codeTypeEngin"),
                                              rs.getString("libelleType"));
                e.setTypeEngin(te);
                engins.add(e);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getEnginsMobilises : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return engins;
    }

    /**
     * Récupère les pompiers appelés pour une intervention.
     */
    public List<Pompier> getPompiersAppeles(int idIntervention) {
        List<Pompier> pompiers = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT p.idPompier, p.nom, p.prenom, p.numeroBip, p.type "
                   + "FROM appeler a "
                   + "INNER JOIN pompier p ON a.idPompier = p.idPompier "
                   + "WHERE a.idIntervention = ? "
                   + "ORDER BY p.nom";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idIntervention);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pompier p = new Pompier();
                p.setIdPompier(rs.getInt("idPompier"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setNumeroBip(rs.getString("numeroBip"));
                p.setType(rs.getString("type"));
                pompiers.add(p);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur getPompiersAppeles : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return pompiers;
    }

    /**
     * Enregistre une nouvelle intervention.
     */
    public int ajouterIntervention(Intervention intervention) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idGenere = -1;

        String sql = "INSERT INTO intervention (lieu, dateHeureAppel, heureArrivee, dureeMinutes) "
                   + "VALUES (?, ?, ?, ?)";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, intervention.getLieu());
            ps.setTimestamp(2, Timestamp.valueOf(intervention.getDateHeureAppel()));
            if (intervention.getHeureArrivee() != null) {
                ps.setTimestamp(3, Timestamp.valueOf(intervention.getHeureArrivee()));
            } else {
                ps.setNull(3, Types.TIMESTAMP);
            }
            ps.setInt(4, intervention.getDureeMinutes());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenere = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur ajouterIntervention : " + e.getMessage());
        } finally {
            ConnexionBdd.fermerTout(rs, ps, con);
        }
        return idGenere;
    }

    /**
     * Ajoute un engin mobilisé à une intervention.
     */
    public boolean ajouterEnginIntervention(int idIntervention, int idEngin) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO mobiliser (idIntervention, idEngin) VALUES (?, ?)";
        try {
            con = ConnexionBdd.ouvrirConnexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idIntervention);
            ps.setInt(2, idEngin);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[SDIS] Erreur ajouterEnginIntervention : " + e.getMessage());
            return false;
        } finally {
            ConnexionBdd.fermer(ps);
            ConnexionBdd.fermer(con);
        }
    }

    private Intervention mapperIntervention(ResultSet rs) throws SQLException {
        Intervention i = new Intervention();
        i.setIdIntervention(rs.getInt("idIntervention"));
        i.setLieu(rs.getString("lieu"));

        Timestamp tsAppel = rs.getTimestamp("dateHeureAppel");
        if (tsAppel != null) i.setDateHeureAppel(tsAppel.toLocalDateTime());

        Timestamp tsArrivee = rs.getTimestamp("heureArrivee");
        if (tsArrivee != null) i.setHeureArrivee(tsArrivee.toLocalDateTime());

        i.setDureeMinutes(rs.getInt("dureeMinutes"));
        return i;
    }
}
