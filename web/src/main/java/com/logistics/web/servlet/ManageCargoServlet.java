package com.logistics.web.servlet;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.entity.User;
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
import java.util.List;

@WebServlet("/manageCargo")
public class ManageCargoServlet extends HttpServlet {

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
                List<Cargo> cargos = cargoServiceRemote.getCargosByShipment(shipment);
                request.setAttribute("shipment", shipment);
                request.setAttribute("cargos", cargos);
                request.getRequestDispatcher("/manageCargo.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
