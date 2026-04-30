package bts.sio.magicien.servlet;

import bts.sio.magicien.dao.InterventionDao;
import bts.sio.magicien.model.Intervention;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class InterventionServlet extends HttpServlet {

    private InterventionDao intervDao = new InterventionDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getRequestURI();
        
        // Mapping pour lister les casernes
        if (url.contains("/InterventionServlet/listerInterventions")) {
            
            // On récupère la liste via le DAO
            List<Intervention> lesInterventions = intervDao.getToutesLesInterventions();
            
            // On la passe à la requête
            request.setAttribute("mesInterventions", lesInterventions);
            
            // On appelle la vue (le fichier JSP)
            this.getServletContext().getRequestDispatcher("/vues/intervention/listerInterventions.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if ("ajouter".equals(action)) {

            } else if ("ajouterEngin".equals(action)) {
                int idInter = Integer.parseInt(request.getParameter("idIntervention"));
                int idEngin = Integer.parseInt(request.getParameter("idEngin"));
                intervDao.ajouterEnginIntervention(idInter, idEngin);
                response.sendRedirect(request.getContextPath() + "/intervention?action=detail&id=" + idInter);
            } else {
                response.sendRedirect(request.getContextPath() + "/intervention?action=liste");
            }
        } catch (Exception e) {
            System.err.println("[SDIS] Erreur InterventionServlet.doPost : " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/intervention?action=liste");
        }
    }


}
