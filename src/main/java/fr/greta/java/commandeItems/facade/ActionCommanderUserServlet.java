package fr.greta.java.commandeItems.facade;

import fr.greta.java.user.domain.User;
import fr.greta.java.user.facade.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/actionCommanderUser")
public class ActionCommanderUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO test = (UserDTO) session.getAttribute("userConnected");
        Map<String, String[]> map = request.getParameterMap();

        for (String key : map.keySet()) {

            System.out.println(key + " : " + map.get(key) [0] );
            System.out.println(test.getName());
            System.out.println(test.getId());
        }



        //User user = (User)session.getAttribute("userConnected");


    }

}
