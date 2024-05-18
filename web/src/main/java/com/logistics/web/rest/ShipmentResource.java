package com.logistics.web.rest;

import com.logistics.ejb.entity.Shipment;
import com.logistics.ejb.exception.ShipmentException;
import com.logistics.ejb.service.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/shipments")
public class ShipmentResource {
    @EJB
    private ShipmentService shipmentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipment> getAllShipments() {
        return shipmentService.getAllShipments();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShipment(Shipment shipment) {
        try {
            shipmentService.scheduleShipment(shipment);
            return Response.ok().build();
        } catch (ShipmentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShipmentById(@PathParam("id") Long id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(shipment).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateShipment(@PathParam("id") Long id, Shipment shipment) {
        shipment.setId(id);
        try {
            shipmentService.updateShipment(shipment);
            return Response.ok().build();
        } catch (ShipmentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response cancelShipment(@PathParam("id") Long id) {
        shipmentService.cancelShipment(id);
        return Response.ok().build();
    }
}