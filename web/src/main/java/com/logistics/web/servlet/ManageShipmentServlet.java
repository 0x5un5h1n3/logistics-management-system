package com.logistics.web.servlet;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.entity.User;
import com.logistics.ejb.remote.ShipmentServiceRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageShipment")
public class ManageShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentServiceRemote shipmentServiceRemote;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                List<Shipment> shipments = shipmentServiceRemote.getAllShipments();
                request.setAttribute("shipments", shipments);
                request.getRequestDispatcher("/manageShipment.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
