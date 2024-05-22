package com.logistics.web.servlet;

import com.logistics.ejb.remote.ShipmentServiceRemote;
import com.logistics.ejb.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cancelShipment")
public class CancelShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentServiceRemote shipmentServiceRemote;

    @EJB
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                Long shipmentId = Long.parseLong(request.getParameter("shipmentId"));
                shipmentServiceRemote.cancelShipment(shipmentId);
                response.sendRedirect("manageShipment.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
