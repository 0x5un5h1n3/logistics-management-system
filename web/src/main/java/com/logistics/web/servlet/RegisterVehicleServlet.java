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

@WebServlet("/registerVehicle")
public class CreateVehicleServlet extends HttpServlet {

    @EJB
    private VehicleServiceRemote vehicleServiceRemote;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                request.getRequestDispatcher("/registerVehicle.jsp").forward(request, response);
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
                String type = request.getParameter("type");
                String licensePlate = request.getParameter("licensePlate");
                double capacity = Double.parseDouble(request.getParameter("capacity"));

                Vehicle vehicle = new Vehicle(type, licensePlate, capacity);
                vehicleServiceRemote.registerVehicle(vehicle);

                response.sendRedirect("manageVehicles.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
