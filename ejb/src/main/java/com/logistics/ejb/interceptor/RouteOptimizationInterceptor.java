package com.logistics.ejb.interceptor;

import com.logistics.ejb.entity.Shipment;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteOptimizationInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RouteOptimizationInterceptor.class);

    @AroundInvoke
    public Object optimizeRoute(InvocationContext ic) throws Exception {
        if (ic.getMethod().getName().equals("scheduleShipment")) {
            Shipment shipment = (Shipment) ic.getParameters()[0];
            
            logger.info("Optimizing route for shipment from {} to {}", shipment.getOrigin(), shipment.getDestination());
        }
        return ic.proceed();
    }
}
