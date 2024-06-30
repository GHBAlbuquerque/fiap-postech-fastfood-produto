package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.dto.request.CreateProductRequest;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import com.fiap.fastfood.core.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void givenProductCreationRequestThenRespondWithStatusCreated() {
        final var createProductRequestMock = new CreateProductRequest()
                .setName("name")
                .setDescription("description")
                .setType("SANDWICH")
                .setPrice(BigDecimal.ONE);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(createProductRequestMock)
                .when()
                .post("/products")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(JSON);

    }
}

