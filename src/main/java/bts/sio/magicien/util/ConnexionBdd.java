package bts.sio.magicien.util;

import java.sql.*;

/**
 * Classe utilitaire pour la gestion de la connexion à la base de données MariaDB.
 * Centralise l'ouverture et la fermeture des ressources JDBC.
 *
 * @author Equipe Magicien
 * @version 1.0
 * @since 2025-03-15
 */
public class ConnexionBdd {

    // Paramètres de connexion - à adapter selon l'environnement
    private static final String URL = "jdbc:mariadb://127.0.0.1:3307/magicienpompier";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Pilote MariaDB JDBC chargé avec succès");
        } catch (ClassNotFoundException e) {
            System.err.println("ERREUR : Pilote MariaDB introuvable !");
            e.printStackTrace();
        }
    }

    /**
     * Ouvre et retourne une nouvelle connexion à la base de données.
     *
     * @return Connection active vers MariaDB
     * @throws SQLException en cas d'échec de connexion
     */
    public static Connection ouvrirConnexion() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connexion BDD ouverte");
        return connection;
    }

    /**
     * Ferme un ResultSet de manière sécurisée.
     */
    public static void fermer(ResultSet rs) {
        if (rs != null) {
            try { rs.close(); }
            catch (SQLException e) {
                System.err.println("Erreur fermeture ResultSet : " + e.getMessage());
            }
        }
    }

    /**
     * Ferme un Statement de manière sécurisée.
     */
    public static void fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) {
                System.err.println("Erreur fermeture Statement : " + e.getMessage());
            }
        }
    }

    /**
     * Ferme une Connection de manière sécurisée.
     */
    public static void fermer(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connexion BDD fermée");
            } catch (SQLException e) {
                System.err.println("Erreur fermeture Connection : " + e.getMessage());
            }
        }
    }

    /**
     * Ferme toutes les ressources JDBC dans l'ordre inverse.
     */
    public static void fermerTout(ResultSet rs, Statement stmt, Connection con) {
        fermer(rs);
        fermer(stmt);
        fermer(con);
    }
}
