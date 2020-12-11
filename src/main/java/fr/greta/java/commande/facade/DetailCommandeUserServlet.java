package fr.greta.java.commande.facade;




import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.persistence.CommandeEntity;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.cuisto.domain.CuistoService;
import fr.greta.java.cuisto.persistence.CuistoRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/detailCommandeUser")
public class DetailCommandeUserServlet extends HttpServlet {


    CommandeRepository repository = new CommandeRepository();
    CommandeDTOWrapper wrapper = new CommandeDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int commandeId = Integer.parseInt(request.getParameter("commande_id"));
        try {
           CommandeDTO commandeDTO = wrapper.toDTOByEntity(repository.findById(commandeId));
            request.setAttribute("commande", commandeDTO);
            request.getRequestDispatcher("detailCommandeUser.jsp").forward(request, response);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

    }
}

