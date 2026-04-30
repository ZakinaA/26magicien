<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bts.sio.magicien.model.Caserne"%>
<%@page import="bts.sio.magicien.model.Pompier"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Détails de la Caserne</title>
        <style>
            table { border-collapse: collapse; width: 100%; margin-top: 20px; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #f2f2f2; }
        </style>
    </head>
    <body>
        <h1>Détails de la caserne</h1>
        
        <%
            // Récupération de la caserne
            Caserne c = (Caserne) request.getAttribute("laCaserne");
            if (c != null) {
        %>
            <ul>
                <li><strong>Nom :</strong> <%= c.getNom() %>[cite: 1]</li>
                <li><strong>Adresse :</strong> <%= c.getRue() %>[cite: 1]</li>
                <li><strong>Ville :</strong> <%= c.getVille() %> (<%= c.getCodePostal() %>)[cite: 1]</li>
            </ul>

            <hr>
            <h2>Personnel affecté</h2>
            <table>
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Type</th>
                        <th>Statut</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Récupération de la liste typée Object
                        List<Object> sesPompiers = (List<Object>) request.getAttribute("sesPompiers");
                        
                        if (sesPompiers != null && !sesPompiers.isEmpty()) {
                            for (Object obj : sesPompiers) {
                                // On vérifie si l'objet est bien un Pompier avant de l'afficher
                                if (obj instanceof Pompier) {
                                    Pompier p = (Pompier) obj;
                    %>
                        <tr>
                            <td><%= p.getNom() %>[cite: 1]</td>
                            <td><%= p.getPrenom() %>[cite: 1]</td>
                            <td><%= p.getType() %>[cite: 1]</td>
                            <td><%= p.getStatut() %>[cite: 1]</td>
                        </tr>
                    <% 
                                }
                            }
                        } else { 
                    %>
                        <tr>
                            <td colspan="4" style="text-align: center;">Aucun pompier affecté à cette caserne.</td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

        <% } else { %>
            <p style="color:red;">Erreur : Aucune donnée de caserne n'a été transmise.</p>
        <% } %>

        <hr>
        <p>
            <a href="${pageContext.request.contextPath}/ServletCaserne/listerCasernes">Retour à la liste</a>
        </p>
    </body>
</html>