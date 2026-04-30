<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil - Gestion Pompier</title>
        <style>
            body { font-family: Arial, sans-serif; text-align: center; padding-top: 50px; }
            .menu-container { display: flex; justify-content: center; gap: 20px; }
            .card { 
                border: 1px solid #ccc; padding: 20px; border-radius: 8px; 
                width: 200px; text-decoration: none; color: black; background: #f9f9f9;
            }
            .card:hover { background: #e0e0e0; border-color: #d32f2f; }
            h1 { color: #d32f2f; }
        </style>
    </head>
    <body>
        <h1>Système de Gestion Caserne</h1>
        <p>Bienvenue sur l'application de gestion. Choisissez une option :</p>

        <div class="menu-container">
            <a href="ServletCaserne/listerCasernes" class="card">
                <h3>Casernes</h3>
                <p>Consulter la liste des casernes</p>
            </a>

            <a href="PompierServlet/listePompiers" class="card">
                <h3>Pompiers</h3>
                <p>Gérer le personnel pompier</p>
            </a>
            
            <a href="InterventionServlet/listerInterventions" class="card">
                <h3>Interventions</h3>
                <p>Gérer les Interventions</p>
            </a>
        </div>
    </body>
</html>