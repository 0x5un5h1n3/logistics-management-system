package com.logistics.web.servlet;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.entity.User;
import com.logistics.ejb.service.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/createShipment")
public class CreateShipmentServlet extends HttpServlet {

    @EJB
    private ShipmentService shipmentService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                request.getRequestDispatcher("/createShipment.jsp").forward(request, response);
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
                String origin = request.getParameter("origin");
                String destination = request.getParameter("destination");
                String shippingDateStr = request.getParameter("shippingDate");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date shippingDate;
                try {
                    shippingDate = dateFormat.parse(shippingDateStr);
                } catch (ParseException e) {
                    request.setAttribute("error", "Invalid shipping date format");
                    request.getRequestDispatcher("/createShipment.jsp").forward(request, response);
                    return;
                }

                Shipment shipment = new Shipment(origin, destination, shippingDate);
                shipmentService.scheduleShipment(shipment);

                response.sendRedirect("manageShipment");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
