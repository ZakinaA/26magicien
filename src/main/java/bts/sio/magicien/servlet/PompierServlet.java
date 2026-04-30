package bts.sio.magicien.servlet;

import bts.sio.magicien.dao.PompierDao;
import bts.sio.magicien.model.Pompier;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PompierServlet extends HttpServlet {

    private PompierDao pompierDao;

    @Override
    public void init() throws ServletException {
        pompierDao = new PompierDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // On récupère la liste typée
        List<Pompier> pompiers = pompierDao.getTousLesPompiers();
        request.setAttribute("pompiers", pompiers);
        
        // On pointe vers le bon fichier physique
        request.getRequestDispatcher("/vues/pompier/listePompiers.jsp").forward(request, response);
    }
}