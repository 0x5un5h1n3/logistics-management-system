package com.logistics.ejb.timer;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.service.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        // Implement logic to optimize the route for the shipment
        logger.info("Optimizing route for shipment from {} to {}", shipment.getOrigin(), shipment.getDestination());
    }
}