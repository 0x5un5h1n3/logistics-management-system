package com.logistics.web.servlet;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.entity.Vehicle;
import com.logistics.ejb.service.VehicleService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/editVehicle")
public class EditVehicleServlet extends HttpServlet {

    @EJB
    private VehicleService vehicleService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                Long vehicleId = Long.parseLong(request.getParameter("id"));
                Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

                if (vehicle == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Vehicle not found");
                    return;
                }

                request.setAttribute("vehicle", vehicle);
                request.getRequestDispatcher("/editVehicle.jsp").forward(request, response);
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
                Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
                String type = request.getParameter("type");
                String licensePlate = request.getParameter("licensePlate");
                double capacity = Double.parseDouble(request.getParameter("capacity"));

                Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
                vehicle.setType(type);
                vehicle.setLicensePlate(licensePlate);
                vehicle.setCapacity(capacity);

                vehicleService.updateVehicle(vehicle);
                response.sendRedirect("manageVehicles.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
