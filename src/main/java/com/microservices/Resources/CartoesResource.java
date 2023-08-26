package com.microservices.Resources;

import com.microservices.Models.Cartoes;
import com.microservices.Services.CartoesService;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("cartoes")
public class CartoesResource {

    @Inject
    CartoesService cartoesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Cartoes> getAllCartoes() {
        return cartoesService.getAllCartoes();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Cartoes> getCartaoById(@PathParam("id") Long id) {
        return cartoesService.getCartaoById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> createCartao(Cartoes cartao) {
        return cartoesService.createCartao(cartao)
                .onItem().transform(id -> Response.status(Response.Status.CREATED).entity(id).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateCartao(@PathParam("id") Long id, Cartoes cartao) {
        return cartoesService.updateCartao(id, cartao)
                .onItem().transform(updatedCartao -> updatedCartao != null ? Response.ok(updatedCartao) : Response.status(Response.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteCartao(@PathParam("id") Long id) {
        return cartoesService.deleteCartao(id)
                .onItem().transform(deleted -> deleted ? Response.noContent() : Response.status(Response.Status.NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/debito")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Cartoes> getCartoesDebito() {
        return cartoesService.getDebitoCartoes();
    }

    @GET
    @Path("/credito")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Cartoes> getCartoesCredito() {
        return cartoesService.getCreditoCartoes();
    }
}
