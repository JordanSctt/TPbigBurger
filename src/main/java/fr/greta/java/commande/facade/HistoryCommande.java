package fr.greta.java.commande.facade;

import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/historyCommande")
public class HistoryCommande extends HttpServlet {

    CommandeRepository repository = new CommandeRepository();
    CommandeDTOWrapper wrapperDTO = new CommandeDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User userEnCours = (User) session.getAttribute("userConnected");
        List <CommandeDTO> commandeDTO = new ArrayList<>();

        try {
           commandeDTO = wrapperDTO.toListDTO(repository.findAllCommandesByUserID(userEnCours.getId()));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        request.setAttribute("commande", commandeDTO);
        request.getRequestDispatcher("historyCommande.jsp").forward(request, response);

    }
}
