package com.github.ropdias.viacep;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import io.smallrye.mutiny.Uni;

@Path("/{CEP}/json")
@RegisterRestClient(configKey = "ViaCEP-api")
public interface ViaCEPService {

    @GET
    Address getAddress(@PathParam("CEP") String CEP);

    @GET
    Uni<Address> getAddressAsync(@PathParam("CEP") String CEP);

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() != 200) {
            throw new RuntimeException("The remote service responded with HTTP " + response.getStatus() + " !");
        }
        return null;
    }
}