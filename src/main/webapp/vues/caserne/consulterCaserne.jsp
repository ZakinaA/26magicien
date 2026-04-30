<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bts.sio.magicien.model.Caserne"%>
<%@page import="bts.sio.magicien.model.Pompier"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Détails de la Caserne</title>
    </head>
    <body>
        <h1>Fiche descriptive de la caserne</h1>
        
        <%
            // Récupération de la caserne transmise par la Servlet
            Caserne c = (Caserne) request.getAttribute("laCaserne");
            if (c != null) {
        %>
            <!-- Tableau des informations générales -->
            <table border="1">
                <tr>
                    <th>Nom de la caserne</th>
                    <td><%= c.getNom() %></td>
                </tr>
                <tr>
                    <th>Adresse</th>
                    <td><%= c.getRue() %></td>
                </tr>
                <tr>
                    <th>Ville</th>
                    <td><%= c.getVille() %> (<%= c.getCodePostal() %>)</td>
                </tr>
            </table>

            <h2>Personnel affecté</h2>
            <table border="1">
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
                        // Récupération de la liste des pompiers
                        List<Object> sesPompiers = (List<Object>) request.getAttribute("sesPompiers");
                        
                        if (sesPompiers != null && !sesPompiers.isEmpty()) {
                            for (Object obj : sesPompiers) {
                                if (obj instanceof Pompier) {
                                    Pompier p = (Pompier) obj;
                    %>
                        <tr>
                            <td><%= p.getNom() %></td>
                            <td><%= p.getPrenom() %></td>
                            <td><%= p.getType() %></td>
                            <td><%= p.getStatut() %></td>
                        </tr>
                    <% 
                                }
                            }
                        } else { 
                    %>
                        <tr>
                            <td colspan="4" align="center">
                                Aucun pompier n'est actuellement affecté à cette caserne.
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

        <% } else { %>
            <p><strong>Erreur :</strong> Impossible de charger les données de la caserne.</p>
        <% } %>

        <p>
            <a href="${pageContext.request.contextPath}/ServletCaserne/listerCasernes">
                ← Retour à la liste des casernes
            </a>
        </p>
    </body>
</html>