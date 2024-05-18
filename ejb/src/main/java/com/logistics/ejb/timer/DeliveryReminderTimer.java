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

import java.util.Date;
import java.util.List;

@Stateless
public class DeliveryReminderTimer {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryReminderTimer.class);

    @EJB
    private ShipmentService shipmentService;

    @Schedule(hour = "*", minute = "*/15", second = "0", persistent = false)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void sendDeliveryReminders() {
        try {
            Date currentDate = new Date();
            List<Shipment> upcomingShipments = shipmentService.getUpcomingShipments(currentDate);
            for (Shipment shipment : upcomingShipments) {
                sendReminderForShipment(shipment);
            }
        } catch (Exception e) {
            logger.error("Error during delivery reminder processing", e);
        }
    }

    private void sendReminderForShipment(Shipment shipment) {
        try {
            // Implement logic to send delivery reminder for the shipment
            logger.info("Sending delivery reminder for shipment from {} to {} on {}",
                    shipment.getOrigin(), shipment.getDestination(), shipment.getShippingDate());
        } catch (Exception e) {
            logger.error("Error during delivery reminder sending for shipment {}", shipment.getId(), e);
        }
    }
}