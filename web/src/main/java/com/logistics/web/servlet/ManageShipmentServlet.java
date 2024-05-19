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
import java.util.List;

@WebServlet("/manageShipment")
public class ManageShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentService shipmentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Shipment> shipments = shipmentService.getAllShipments();
        request.setAttribute("shipments", shipments);
        request.getRequestDispatcher("/manageShipment.jsp").forward(request, response);
    }
}
