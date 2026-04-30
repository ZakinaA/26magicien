<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="bts.sio.magicien.model.Caserne"%>
<!DOCTYPE html>
<html>
<<<<<<< HEAD
<head>
    <meta charset="UTF-8">
    <title>Liste des Casernes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vues/css/style.css">
</head>
<body>
=======
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
>>>>>>> consulter-intervention

<%
    List<Caserne> lesCasernes = (List<Caserne>) request.getAttribute("mesCasernes");
    int count = (lesCasernes != null) ? lesCasernes.size() : 0;
%>

<div class="page-header">
    <h1>Liste des casernes</h1>
    <span class="badge-count"><%= count %> caserne(s)</span>
</div>

<div class="red-line"></div>

<div class="table-wrap">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Adresse</th>
                <th>Code Postal</th>
                <th>Ville</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (lesCasernes != null && !lesCasernes.isEmpty()) {
                    for (Caserne c : lesCasernes) {
            %>
            <tr>
                <td class="id-col"><%= c.getIdCaserne() %></td>
                <td class="nom-col"><%= c.getNom() %></td>
                <td class="adresse-col"><%= c.getRue() %></td>
                <td class="cp-col"><%= c.getCodePostal() %></td>
                <td class="ville-col"><%= c.getVille() %></td>
                <td>
                    <a class="btn-detail" href="<%= request.getContextPath() %>/caserne/detail?id=<%= c.getIdCaserne() %>">Détail</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr class="empty-row">
                <td colspan="6">Aucune donnée reçue de la servlet.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<a class="back-link" href="${pageContext.request.contextPath}/">← Retour à l'accueil</a>

</body>
</html>