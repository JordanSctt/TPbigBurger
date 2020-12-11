package fr.greta.java.cuisto.facade;

import fr.greta.java.cuisto.domain.CuistoService;
import fr.greta.java.cuisto.domain.CuistoWrapper;
import fr.greta.java.cuisto.persistence.CuistoRepository;
import fr.greta.java.generic.exception.RepositoryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gestionCuisto")
public class AffichageCuistoServlet extends HttpServlet {

        CuistoService service = new CuistoService();
        CuistoWrapper wrapper = new CuistoWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<CuistoDTO> cuistos = wrapper.toDTOS(service.findAllCuisto());

            request.setAttribute("cuistos", cuistos);
            request.getRequestDispatcher("gestionCuisto.jsp").forward(request, response);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }


    }
}
