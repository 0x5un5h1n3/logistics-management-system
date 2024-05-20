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
import java.util.List;

@WebServlet("/manageVehicles")
public class ManageVehiclesServlet extends HttpServlet {

    @EJB
    private VehicleService vehicleService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                List<Vehicle> vehicles = vehicleService.getAllVehicles();
                request.setAttribute("vehicles", vehicles);
                request.getRequestDispatcher("/manageVehicles.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
