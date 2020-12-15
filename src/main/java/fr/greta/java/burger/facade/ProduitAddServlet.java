package fr.greta.java.burger.facade;

import fr.greta.java.burger.domain.Produit;
import fr.greta.java.burger.domain.ProduitService;
import fr.greta.java.burger.domain.ProduitType;
import fr.greta.java.generic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajoutProduit")
public class ProduitAddServlet extends HttpServlet {

    private static final String LABEL = "label_parameter";
    private static final String PRICE = "price_parameter";
    private static final String TYPE = "TYPE_parameter";

    private static ProduitService produitService = new ProduitService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Produit produit = new Produit();

            produit.setLabel(request.getParameter(LABEL));
            produit.setPrice(Double.parseDouble(request.getParameter(PRICE)));
            produit.setProduitType(ProduitType.valueOf(request.getParameter(TYPE)));
            produitService.create(produit);

            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}
