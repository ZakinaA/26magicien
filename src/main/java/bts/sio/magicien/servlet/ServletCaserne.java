package bts.sio.magicien.servlet;

import bts.sio.magicien.dao.CaserneDao;
import bts.sio.magicien.model.Caserne;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet pour piloter la consultation des casernes
 * @author Robin
 */
public class ServletCaserne extends HttpServlet {

    private CaserneDao caserneDao = new CaserneDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getRequestURI();
        
        // Mapping pour lister les casernes
        if (url.contains("/ServletCaserne/listerCasernes")) {
            
            // On récupère la liste via le DAO
            List<Caserne> lesCasernes = caserneDao.getToutesLesCasernes();
            
            // On la passe à la requête
            request.setAttribute("mesCasernes", lesCasernes);
            
            // On appelle la vue (le fichier JSP)
            this.getServletContext().getRequestDispatcher("/vues/caserne/listerCasernes.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}