package com.logistics.web.servlet;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerServlet.class);

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                handleErrorRequest(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void handleErrorRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String servletName = (String) request.getAttribute("jakarta.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        logger.error("Error occurred while processing request: {} ({})", requestUri, statusCode);
        if (throwable != null) {
            logger.error("Error message: {}", throwable.getMessage());
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Error</title></head><body>");
        out.write("<h1>Error</h1>");
        out.write("<p>Status code: " + statusCode + "</p>");
        out.write("<p>Servlet name: " + servletName + "</p>");
        out.write("<p>Request URI: " + requestUri + "</p>");
        if (throwable != null) {
            out.write("<p>Error message: " + throwable.getMessage() + "</p>");
        }
        out.write("</body></html>");
    }
}
