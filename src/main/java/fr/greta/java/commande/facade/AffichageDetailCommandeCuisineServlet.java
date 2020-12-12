package fr.greta.java.commande.facade;

import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/detailCommandeCuisine")
public class AffichageDetailCommandeCuisineServlet extends HttpServlet {


    CommandeRepository repository = new CommandeRepository();
    CommandeDTOWrapper wrapper = new CommandeDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int commandeId = Integer.parseInt(request.getParameter("commande_id"));
        try {
            CommandeDTO commandeDTO = wrapper.toDTOByEntity(repository.findById(commandeId));
            request.setAttribute("commande", commandeDTO);

         // response.sendRedirect(request.getContextPath() + "/detailCommandeCuisine.jsp");
         request.getRequestDispatcher("/detailCommandeCuisine.jsp").forward(request, response);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }
}