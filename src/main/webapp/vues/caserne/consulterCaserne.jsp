<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bts.sio.magicien.model.Caserne"%>
<%@page import="bts.sio.magicien.model.Pompier"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails de la Caserne</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vues/css/style.css">
</head>
<body>

<%
    Caserne c = (Caserne) request.getAttribute("laCaserne");
    if (c != null) {
%>

<div class="page-header">
    <h1>Fiche descriptive de la caserne</h1>
</div>
<div class="red-line"></div>

<div class="fiche-table">
    <table>
        <tr>
            <th>Nom de la caserne</th>
            <td class="nom-col"><%= c.getNom() %></td>
        </tr>
        <tr>
            <th>Adresse</th>
            <td class="adresse-col"><%= c.getRue() %></td>
        </tr>
        <tr>
            <th>Ville</th>
            <td class="ville-col"><%= c.getVille() %> (<%= c.getCodePostal() %>)</td>
        </tr>
    </table>
</div>

<h2>Personnel affecté</h2>

<div class="table-wrap">
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
                List<Object> sesPompiers = (List<Object>) request.getAttribute("sesPompiers");
                if (sesPompiers != null && !sesPompiers.isEmpty()) {
                    for (Object obj : sesPompiers) {
                        if (obj instanceof Pompier) {
                            Pompier p = (Pompier) obj;
            %>
            <tr>
                <td class="nom-col"><%= p.getNom() %></td>
                <td class="prenom-col"><%= p.getPrenom() %></td>
                <td class="type-col"><%= p.getType() %></td>
                <td><%= p.getStatut() %></td>
            </tr>
            <%
                        }
                    }
                } else {
            %>
            <tr class="empty-row">
                <td colspan="4">Aucun pompier n'est actuellement affecté à cette caserne.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<% } else { %>
<div class="page-header">
    <h1>Fiche descriptive de la caserne</h1>
</div>
<div class="red-line"></div>
<p class="error-msg"><strong>Erreur :</strong> Impossible de charger les données de la caserne.</p>
<% } %>

<a class="back-link" href="${pageContext.request.contextPath}/ServletCaserne/listerCasernes">← Retour à la liste des casernes</a>

</body>
</html>