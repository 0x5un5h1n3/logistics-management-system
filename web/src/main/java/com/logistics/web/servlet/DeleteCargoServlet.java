package com.logistics.web.servlet;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.remote.CargoServiceRemote;
import com.logistics.ejb.service.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/deleteCargo")
public class DeleteCargoServlet extends HttpServlet {

    @EJB
    private CargoServiceRemote cargoServiceRemote;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                Long cargoId = Long.parseLong(request.getParameter("id"));
                cargoServiceRemote.deleteCargo(cargoId);
                response.sendRedirect("manageCargo?shipmentId=" + request.getParameter("shipmentId"));
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
