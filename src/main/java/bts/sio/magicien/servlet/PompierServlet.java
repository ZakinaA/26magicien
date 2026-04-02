package bts.sio.magicien.servlet;

import bts.sio.magicien.dao.PompierDao;
import bts.sio.magicien.model.Pompier;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet pour afficher la liste des pompiers.
 * @author Equipe Magicien
 * @version 1.0
 * @since 2025-03-15
 */

public class PompierServlet extends HttpServlet {

    private PompierDao pompierDao;

    @Override
    public void init() throws ServletException {
        pompierDao = new PompierDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Pompier> pompiers = pompierDao.getTousLesPompiers();
        request.setAttribute("pompiers", pompiers);
        request.getRequestDispatcher("/WEB-INF/jsp/listePompiers.jsp").forward(request, response);
    }
}