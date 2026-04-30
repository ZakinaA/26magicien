<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bts.sio.magicien.model.Caserne"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Liste des Casernes</title>
    </head>
    <body>
        <h1>Liste des Casernes</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Rue</th>
                    <th>Ville</th>
                    <th>Code Postal</th>
                    <th colspan="2">Actions</th> <!-- Colonne pour les deux liens -->
                </tr>
            </thead>
            <tbody>
                <% 
                    // Récupération de la liste transmise par la servlet
                    List<Caserne> lesCasernes = (List<Caserne> ) request.getAttribute("mesCasernes");
                    
                    if (lesCasernes != null && !lesCasernes.isEmpty()) {
                        for (Caserne c : lesCasernes) {
                %>
                    <tr>
                        <td><%= c.getIdCaserne() %></td>
                        <td><%= c.getNom() %></td>
                        <td><%= c.getRue() %></td>
                        <td><%= c.getVille() %></td>
                        <td><%= c.getCodePostal() %></td>
                        <td>
                            <a href="ConsulterCaserne?id=<%= c.getIdCaserne() %>">Consulter</a>
                        </td>
                        <td>
                            <a href="SupprimerCaserne?id=<%= c.getIdCaserne() %>" 
                               onclick="return confirm('Confirmer la suppression ?');" 
                               style="color:red;">Supprimer</a>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="7" style="text-align: center;">Aucune donnée reçue de la servlet.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <p>
            <a href="${pageContext.request.contextPath}/index.html">Retour à l'accueil</a>
        </p>
    </body>
</html>