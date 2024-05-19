package com.logistics.web.servlet;

import com.logistics.ejb.service.ShipmentService;
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
    private ShipmentService shipmentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long shipmentId = Long.parseLong(request.getParameter("shipmentId"));
        shipmentService.cancelShipment(shipmentId);
        response.sendRedirect("manageShipment.jsp");
    }
}
