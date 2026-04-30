<%-- 
    Document   : listePompier
    Created on : 2 avr. 2026, 09:13:52
    Author     : ts1sio
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des pompiers</title>
</head>
<body>

<h1>Liste des pompiers</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prenom</th>
        <th>Date de naissance</th>
        <th>Type</th>
        <th>N° Bip</th>
    </tr>
    <c:forEach var="p" items="${pompiers}">
        <tr>
            <td>${p.idPompier}</td>
            <td>${p.nom}</td>
            <td>${p.prenom}</td>
            <td>${p.dateNaissance}</td>
            <td>${p.type}</td>
            <td>${p.numeroBip}</td>
        </tr>
    </c:forEach>
</table>
        <p>
            <a href="${pageContext.request.contextPath}/">Retour à l'accueil</a>
        </p>
</body>
</html>


