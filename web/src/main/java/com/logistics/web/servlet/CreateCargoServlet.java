package com.logistics.web.servlet;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.entity.User;
import com.logistics.ejb.exception.CargoException;
import com.logistics.ejb.remote.CargoServiceRemote;
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

@WebServlet("/createCargo")
public class CreateCargoServlet extends HttpServlet {

    @EJB
    private CargoServiceRemote cargoServiceRemote;

    @EJB
    private ShipmentServiceRemote shipmentServiceRemote;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                Long shipmentId = Long.parseLong(request.getParameter("shipmentId"));
                Shipment shipment = shipmentServiceRemote.getShipmentById(shipmentId);
                if (shipment == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shipment not found");
                    return;
                }
                request.setAttribute("shipment", shipment);
                request.getRequestDispatcher("/createCargo.jsp").forward(request, response);
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
            if (userService.isAuthenticated(user)) {
                String description = request.getParameter("description");
                double weight = Double.parseDouble(request.getParameter("weight"));
                Long shipmentId = Long.parseLong(request.getParameter("shipmentId"));
                Shipment shipment = shipmentServiceRemote.getShipmentById(shipmentId);
                if (shipment == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Shipment not found");
                    return;
                }
                Cargo cargo = new Cargo(description, weight);
                try {
                    cargoServiceRemote.createCargo(cargo, shipment);
                    response.sendRedirect("manageCargo?shipmentId=" + shipmentId);
                } catch (CargoException e) {
                    request.setAttribute("error", e.getMessage());
                    request.getRequestDispatcher("/createCargo.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
