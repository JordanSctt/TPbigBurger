package fr.greta.java.produit.facade;

import fr.greta.java.produit.domain.Produit;
import fr.greta.java.produit.domain.ProduitService;
import fr.greta.java.generic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/CommandeUserLivraison")
public class ProduitServletControlerLivraison extends HttpServlet {

    private ProduitService service = new ProduitService();

    private ProduitDTOWrapper wrapper = new ProduitDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        if (session.getAttribute("userConnected") != null) {
            try {
                List<Produit> produits = service.findAllWithProduit();
                request.setAttribute("produits", wrapper.toDTOS(produits));
                request.getRequestDispatcher("commandeLivraison.jsp").forward(request, response);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        }


    }

}
