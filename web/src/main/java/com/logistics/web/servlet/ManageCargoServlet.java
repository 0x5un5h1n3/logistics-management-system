package com.logistics.web.servlet;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.service.CargoService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageCargo")
public class ManageCargoServlet extends HttpServlet {

    @EJB
    private CargoService cargoService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cargo> cargos = cargoService.getAllCargo();
        request.setAttribute("cargos", cargos);
        request.getRequestDispatcher("/manageCargo.jsp").forward(request, response);
    }
}
