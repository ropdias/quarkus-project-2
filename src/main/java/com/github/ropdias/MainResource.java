package com.github.ropdias;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestPath;

import com.github.ropdias.viacep.Address;
import com.github.ropdias.viacep.ViaCEPService;

import io.smallrye.mutiny.Uni;

@Path("")
public class MainResource {

    @Inject
    @RestClient
    ViaCEPService viaCEPService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sync/{CEP}")
    public Address syncCEP(@RestPath String CEP) {
        try {
            return viaCEPService.getAddress(CEP);
        } catch (Exception e) {
            return new Address(e.getMessage());
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{CEP}")
    public Uni<Address> asyncCEP(@RestPath String CEP) {
        return viaCEPService.getAddressAsync(CEP).onFailure().recoverWithItem(e -> new Address(e.getCause().getMessage()));
        // newAddress = objectMapper.writeValueAsString(address);
    }
}