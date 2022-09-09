package com.github.ropdias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.junit.jupiter.api.Test;

import com.github.ropdias.resources.WireMockExtensions;
import com.github.ropdias.viacep.Address;
import com.github.ropdias.viacep.ViaCEPService;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
public class ViaCEPServiceTest {

    @Inject
    @RestClient
    ViaCEPService viaCEPService;

    @Test
    public void testGetAddress() {
        Address address = viaCEPService.getAddress("01001000");
        assertEquals("01001-000", address.getCep());
        assertEquals("Praça da Sé", address.getLogradouro());
        assertEquals("lado ímpar", address.getComplemento());
        assertEquals("Sé", address.getBairro());
        assertEquals("São Paulo", address.getLocalidade());
        assertEquals("SP", address.getUf());
        assertEquals("3550308", address.getIbge());
        assertEquals("1004", address.getGia());
        assertEquals("11", address.getDdd());
        assertEquals("7107", address.getSiafi());
    }
}
