<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des pompiers</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vues/css/style.css">
</head>
<body>

<div class="page-header">
    <h1>Liste des pompiers</h1>
    <span class="badge-count">${fn:length(pompiers)} pompier(s)</span>
</div>

<div class="red-line"></div>

<div class="table-wrap">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date de naissance</th>
                <th>Type</th>
                <th>N° Bip</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${not empty pompiers}">
                    <c:forEach var="p" items="${pompiers}">
                        <tr>
                            <td class="id-col">${p.idPompier}</td>
                            <td class="nom-col">${p.nom}</td>
                            <td class="prenom-col">${p.prenom}</td>
                            <td class="date-col">${p.dateNaissance}</td>
                            <td class="type-col">${p.type}</td>
                            <td class="bip-col">${p.numeroBip}</td>
                            <td>
                                <a class="btn-detail" href="${pageContext.request.contextPath}/pompier/detail?id=${p.idPompier}">Détail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr class="empty-row">
                        <td colspan="7">Aucun pompier trouvé.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

<a class="back-link" href="${pageContext.request.contextPath}/">← Retour à l'accueil</a>

</body>
</html>