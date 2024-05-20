package com.logistics.web.servlet;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.entity.User;
import com.logistics.ejb.service.CargoService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/editCargo")
public class EditCargoServlet extends HttpServlet {

    @EJB
    private CargoService cargoService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAuthenticated()) {
                Long cargoId = Long.parseLong(request.getParameter("id"));
                Cargo cargo = cargoService.getCargoById(cargoId);

                if (cargo == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cargo not found");
                    return;
                }

                request.setAttribute("cargo", cargo);
                request.getRequestDispatcher("/editCargo.jsp").forward(request, response);
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
                Long cargoId = Long.parseLong(request.getParameter("cargoId"));
                String description = request.getParameter("description");
                double weight = Double.parseDouble(request.getParameter("weight"));

                // Input validation
                if (description == null || description.trim().isEmpty() || weight <= 0) {
                    request.setAttribute("error", "Invalid input data");
                    request.getRequestDispatcher("/editCargo.jsp").forward(request, response);
                    return;
                }

                Cargo cargo = cargoService.getCargoById(cargoId);
                cargo.setDescription(description);
                cargo.setWeight(weight);
                cargoService.updateCargo(cargo);
                response.sendRedirect("manageCargo.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
