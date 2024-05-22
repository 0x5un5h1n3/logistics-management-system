package com.logistics.web.servlet;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.entity.User;
import com.logistics.ejb.remote.ShipmentServiceRemote;
import com.logistics.ejb.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/trackShipment")
public class TrackShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentServiceRemote shipmentServiceRemote;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                String trackingNumber = request.getParameter("trackingNumber");
                Shipment shipment = shipmentServiceRemote.getShipmentByTrackingNumber(trackingNumber);

                if (shipment == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shipment not found");
                    return;
                }

                request.setAttribute("shipment", shipment);
                request.getRequestDispatcher("/trackShipment.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
