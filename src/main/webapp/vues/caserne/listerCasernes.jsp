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
                </tr>
            </thead>
            <tbody>
                <% 
                    // Récupération de la liste transmise par la servlet
                    List<Caserne> lesCasernes = (List<Caserne>) request.getAttribute("mesCasernes");
                    
                    if (lesCasernes != null) {
                        for (Caserne c : lesCasernes) {
                %>
                    <tr>
                        <td><%= c.getIdCaserne() %></td>
                        <td><%= c.getNom() %></td>
                        <td><%= c.getRue() %></td>
                        <td><%= c.getVille() %></td>
                        <td><%= c.getCodePostal() %></td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">Aucune donnée reçue de la servlet.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <p>
            <a href="${pageContext.request.contextPath}/index.html">Retour à l'accueil</a>
        </p>
    </body>
</html>