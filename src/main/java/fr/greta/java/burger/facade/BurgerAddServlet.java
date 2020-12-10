package fr.greta.java.burger.facade;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.burger.domain.BurgerService;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajoutBurger")
public class BurgerAddServlet extends HttpServlet {

    private static final String LABEL = "label_parameter";
    private static final String PRICE = "price_parameter";

    private static BurgerService burgerService = new BurgerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Burger burger = new Burger();
            burger.setLabel(request.getParameter(LABEL));
            burger.setPrice(Double.parseDouble(request.getParameter(PRICE)));
            burgerService.create(burger);

            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
