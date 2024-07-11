package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.dto.request.CreateProductRequest;
import com.fiap.fastfood.common.dto.request.UpdateProductRequest;
import org.junit.jupiter.api.Test;
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

    @Test
    public void givenUpdateProductRequestThenRespondWithStatusOk() {
        final var updateProductMock = new UpdateProductRequest()
                .setName("name")
                .setDescription("description")
                .setType("DRINK")
                .setPrice(BigDecimal.ONE);

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .body(updateProductMock)
                .when()
                .put("/products/95ba9aa0-5e0b-4db8-9d6a-4279809e715b")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(JSON);

    }

    @Test
    public void givenUpdateProductDeleteThenRespondWithStatusNoContent() {

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .when()
                .delete("/products/95ba9aa0-5e0b-4db8-9d6a-4279809e715b")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void givenProductTypeThenRespondWithProductList() {

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .when()
                .get("/products?type=SANDWICH")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.OK.value())
                .contentType(JSON);
    }

    @Test
    public void givenProductIdAndTypeThenRespondWithProduct() {

        given()
                .port(port)
                .header("Content-Type", "application/json")
                .when()
                .get("/products/95ba9aa0-5e0b-4db8-9d6a-4279809e715b?type=SANDWICH")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .contentType(JSON);
    }
}

