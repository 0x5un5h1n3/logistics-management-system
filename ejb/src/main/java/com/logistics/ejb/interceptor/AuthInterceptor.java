package com.logistics.ejb.interceptor;

import com.logistics.ejb.entity.User;
import com.logistics.ejb.service.UserService;
import jakarta.ejb.EJB;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthInterceptor {
    @EJB
    private UserService userService;

    @AroundInvoke
    public Object authenticate(InvocationContext ic) throws Exception {
        HttpServletRequest request = (HttpServletRequest) ic.getContextData().get("jakarta.servlet.http.HttpServletRequest");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (userService.isAuthenticated(user)) {
                return ic.proceed();
            }
        }

        // Redirect to login page or handle unauthorized access
        request.getRequestDispatcher("/login.jsp").forward(request, request.getResponse());
        return null;
    }
}
