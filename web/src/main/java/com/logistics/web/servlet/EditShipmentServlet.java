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
import java.time.LocalDate;

@WebServlet("/editShipment")
public class EditShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentServiceRemote shipmentServiceRemote;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                Long shipmentId = Long.parseLong(request.getParameter("id"));
                Shipment shipment = shipmentServiceRemote.getShipmentById(shipmentId);

                if (shipment == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shipment not found");
                    return;
                }

                request.setAttribute("shipment", shipment);
                request.getRequestDispatcher("/editShipment.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                Long shipmentId = Long.parseLong(request.getParameter("shipmentId"));
                String origin = request.getParameter("origin");
                String destination = request.getParameter("destination");
                LocalDate shippingDate = LocalDate.parse(request.getParameter("shippingDate"));

                Shipment shipment = shipmentServiceRemote.getShipmentById(shipmentId);
                shipment.setOrigin(origin);
                shipment.setDestination(destination);
                shipment.setShippingDate(shippingDate);

                shipmentServiceRemote.updateShipment(shipment);
                response.sendRedirect("manageShipment.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
