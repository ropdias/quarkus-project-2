package com.github.ropdias;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import com.github.ropdias.resources.WireMockExtensions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(WireMockExtensions.class)
public class GreetingResourceTest {

    @Test
    public void testSyncCEP01001000() {
        given()
        .pathParam("CEP", "01001000")
                .when().get("/sync/{CEP}")
                .then()
                .statusCode(200)
                .body(is(
                        "{\"cep\":\"01001-000\",\"nomeDaRua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\",\"ibge\":\"3550308\",\"gia\":\"1004\",\"ddd\":\"11\",\"siafi\":\"7107\"}"));
    }

    @Test
    public void testAsyncCEP01001000() {
        given()
        .pathParam("CEP", "01001000")
                .when().get("/{CEP}")
                .then()
                .statusCode(200)
                .body(is(
                        "{\"cep\":\"01001-000\",\"nomeDaRua\":\"Praça da Sé\",\"complemento\":\"lado ímpar\",\"bairro\":\"Sé\",\"localidade\":\"São Paulo\",\"uf\":\"SP\",\"ibge\":\"3550308\",\"gia\":\"1004\",\"ddd\":\"11\",\"siafi\":\"7107\"}"));
    }

    @Test
    public void testSyncError() {
        given()
        .pathParam("CEP", "7761232151")
                .when().get("/sync/{CEP}")
                .then()
                .statusCode(200)
                .body(is("{\"erro\":\"The remote service responded with HTTP 400 !\"}"));
    }

}