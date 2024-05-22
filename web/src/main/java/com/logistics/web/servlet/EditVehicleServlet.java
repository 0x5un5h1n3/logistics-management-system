package com.logistics.web.servlet;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.entity.Vehicle;
import com.logistics.ejb.remote.VehicleServiceRemote;
import com.logistics.ejb.service.UserService;
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
    private VehicleServiceRemote vehicleServiceRemote;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                Long vehicleId = Long.parseLong(request.getParameter("id"));
                Vehicle vehicle = vehicleServiceRemote.getVehicleById(vehicleId);

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
            if (userService.isAuthenticated(user)) {
                Long vehicleId = Long.parseLong(request.getParameter("vehicleId"));
                String type = request.getParameter("type");
                String licensePlate = request.getParameter("licensePlate");
                double capacity = Double.parseDouble(request.getParameter("capacity"));

                Vehicle vehicle = vehicleServiceRemote.getVehicleById(vehicleId);
                vehicle.setType(type);
                vehicle.setLicensePlate(licensePlate);
                vehicle.setCapacity(capacity);

                vehicleServiceRemote.updateVehicle(vehicle);
                response.sendRedirect("manageVehicles.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
