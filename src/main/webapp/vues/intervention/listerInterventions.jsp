<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bts.sio.magicien.model.Intervention"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Liste des Interventions</title>
    </head>
    <body>
        <h1>Liste des Interventions</h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>idIntervention</th>
                    <th>lieu</th>
                    <th>dateHeureAppel</th>
                    <th>heureArrive</th>
                    <th>Code dureeMinutes</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    // Récupération de la liste transmise par la servlet
                    List<Intervention> lesInterventions = (List<Intervention>) request.getAttribute("mesInterventions");
                    
                    if (lesInterventions != null) {
                        for (Intervention i : lesInterventions) {
                %>
                    <tr>
                        <td><%= i.getIdIntervention() %></td>
                        <td><%= i.getLieu() %></td>
                        <td><%= i.getDateHeureAppel() %></td>
                        <td><%= i.getHeureArrivee() %></td>
                        <td><%= i.getDureeMinutes() %></td>
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
            <a href="${pageContext.request.contextPath}/">Retour à l'accueil</a>
        </p>
    </body>
</html>