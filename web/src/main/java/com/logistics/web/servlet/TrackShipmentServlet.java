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

@WebServlet("/trackShipment")
public class TrackShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentService shipmentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String trackingNumber = request.getParameter("trackingNumber");
        Shipment shipment = shipmentService.getShipmentByTrackingNumber(trackingNumber);

        if (shipment == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shipment not found");
            return;
        }

        request.setAttribute("shipment", shipment);
        request.getRequestDispatcher("/trackShipment.jsp").forward(request, response);
    }
}
