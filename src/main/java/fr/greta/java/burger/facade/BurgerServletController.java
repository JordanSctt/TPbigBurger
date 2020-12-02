package fr.greta.java.burger.facade;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.burger.domain.BurgerService;
import fr.greta.java.generic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/CommandeUser")
public class BurgerServletController extends HttpServlet {

    private BurgerService service = new BurgerService();

    private BurgerDTOWrapper wrapper = new BurgerDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();



        try {
            List<Burger> burgers = service.findAllWithBurger();
            request.setAttribute("burgers", wrapper.toDTOS(burgers));
            request.getRequestDispatcher("commande.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }



    }

}
