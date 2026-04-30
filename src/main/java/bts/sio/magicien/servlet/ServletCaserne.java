package bts.sio.magicien.servlet;

import bts.sio.magicien.dao.CaserneDao;
import bts.sio.magicien.model.Caserne;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet pour piloter les actions sur les casernes (Lister, Consulter, Supprimer)
 * @author Robin
 */
@WebServlet(name = "ServletCaserne", urlPatterns = {"/ServletCaserne/*"})
public class ServletCaserne extends HttpServlet {

    private CaserneDao caserneDao = new CaserneDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = request.getRequestURI();
        
        // --- CAS 1 : LISTER ---
        if (url.contains("/ServletCaserne/listerCasernes")) {
            
            List<Caserne> lesCasernes = caserneDao.getToutesLesCasernes();
            request.setAttribute("mesCasernes", lesCasernes);
            
            this.getServletContext().getRequestDispatcher("/vues/caserne/listerCasernes.jsp").forward(request, response);
        } 
        
        // --- CAS 2 : CONSULTER ---
        else if (url.contains("/ServletCaserne/consulterCaserne")) {
            
            // On récupère l'id passé dans l'URL (?id=X)
            int id = Integer.parseInt(request.getParameter("id"));
            Caserne laCaserne = caserneDao.getCaserneParId(id);
            
            request.setAttribute("laCaserne", laCaserne);
            
            this.getServletContext().getRequestDispatcher("/vues/caserne/consulterCaserne.jsp").forward(request, response);
        }
        
        // --- CAS 3 : SUPPRIMER ---
        else if (url.contains("/ServletCaserne/supprimerCaserne")) {
            
            int id = Integer.parseInt(request.getParameter("id"));
            caserneDao.supprimerCaserne(id);
            
            // Après suppression, on redirige vers la liste
            response.sendRedirect(request.getContextPath() + "/ServletCaserne/listerCasernes");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}