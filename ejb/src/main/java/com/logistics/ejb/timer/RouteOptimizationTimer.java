package com.logistics.ejb.timer;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.service.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

@Stateless
public class RouteOptimizationTimer {
    private static final Logger logger = LoggerFactory.getLogger(RouteOptimizationTimer.class);

    @EJB
    private ShipmentService shipmentService;

    @Schedule(hour = "*/6", minute = "0", second = "0", persistent = false)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void optimizeRoutes() {
        List<Shipment> allShipments = shipmentService.getAllShipments();
        for (Shipment shipment : allShipments) {
            optimizeRouteForShipment(shipment);
        }
    }

    private void optimizeRouteForShipment(Shipment shipment) {
        logger.info("Optimizing route for shipment from {} to {}", shipment.getOrigin(), shipment.getDestination());

        String optimizedRoute = calculateOptimizedRoute(shipment.getOrigin(), shipment.getDestination());

        // Update the shipment with the optimized route
        shipment.setOptimizedRoute(optimizedRoute);
        try {
            shipmentService.updateShipment(shipment);
        } catch (ShipmentException e) {
            logger.error("Error updating shipment with optimized route", e);
        }
    }

    private String calculateOptimizedRoute(String origin, String destination) {
        return origin + " -> " + destination;
    }
}
