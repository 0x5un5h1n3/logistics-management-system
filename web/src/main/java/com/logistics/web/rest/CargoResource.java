package com.logistics.web.rest;

import com.logistics.ejb.entity.Cargo;
import com.logistics.ejb.exception.CargoException;
import com.logistics.ejb.service.CargoService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/cargos")
public class CargoResource {
    @EJB
    private CargoService cargoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargo> getAllCargo() {
        return cargoService.getAllCargo();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCargo(Cargo cargo) {
        try {
            cargoService.createCargo(cargo);
            return Response.ok().build();
        } catch (CargoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCargoById(@PathParam("id") Long id) {
        Cargo cargo = cargoService.getCargoById(id);
        if (cargo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cargo).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCargo(@PathParam("id") Long id, Cargo cargo) {
        cargo.setId(id);
        try {
            cargoService.updateCargo(cargo);
            return Response.ok().build();
        } catch (CargoException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCargo(@PathParam("id") Long id) {
        cargoService.deleteCargo(id);
        return Response.ok().build();
    }
}
