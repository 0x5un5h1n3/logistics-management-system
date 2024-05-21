package com.logistics.ejb.service;

import com.logistics.ejb.remote.RouteOptimizationServiceRemote;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless
@Remote(RouteOptimizationServiceRemote.class)
public class RouteOptimizationService implements RouteOptimizationServiceRemote {

    @Override
    public String calculateOptimizedRoute(String origin, String destination) {
        // Returns a dummy route for demonstration purpose
        return origin + " -> " + destination;
    }
}
