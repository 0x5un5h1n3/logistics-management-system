package com.logistics.web.servlet;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.service.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/editShipment")
public class EditShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentService shipmentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long shipmentId = Long.parseLong(request.getParameter("id"));
        Shipment shipment = shipmentService.getShipmentById(shipmentId);

        if (shipment == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shipment not found");
            return;
        }

        request.setAttribute("shipment", shipment);
        request.getRequestDispatcher("/editShipment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long shipmentId = Long.parseLong(request.getParameter("shipmentId"));
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        LocalDate shippingDate = LocalDate.parse(request.getParameter("shippingDate"));

        Shipment shipment = shipmentService.getShipmentById(shipmentId);
        shipment.setOrigin(origin);
        shipment.setDestination(destination);
        shipment.setShippingDate(shippingDate);

        shipmentService.updateShipment(shipment);
        response.sendRedirect("manageShipment.jsp");
    }
}
