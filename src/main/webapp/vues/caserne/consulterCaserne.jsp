<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bts.sio.magicien.model.Caserne"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Détails de la Caserne</title>
    </head>
    <body>
        <h1>Détails de la caserne</h1>
        
        <%
            // Récupération de l'objet "laCaserne" envoyé par la ServletCaserne
            Caserne c = (Caserne) request.getAttribute("laCaserne");
            if (c != null) {
        %>
            <ul>
                <li><strong>Nom :</strong> <%= c.getNom() %></li>
                <li><strong>Adresse :</strong> <%= c.getRue() %></li>
                <li><strong>Ville :</strong> <%= c.getVille() %> (<%= c.getCodePostal() %>)</li>
            </ul>
        <% } else { %>
            <p style="color:red;">Erreur : Aucune donnée de caserne n'a été transmise.</p>
        <% } %>

        <hr>
        <p>
            <a href="${pageContext.request.contextPath}/ServletCaserne/listerCasernes">Retour à la liste</a>
        </p>
    </body>
</html>