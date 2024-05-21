package com.logistics.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface RouteOptimizationServiceRemote {
    String calculateOptimizedRoute(String origin, String destination);
}
