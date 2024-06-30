package com.fiap.fastfood.communication.controllers;

import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Mock
    private ProductGateway gateway;

    @Mock
    private ProductUseCase useCase;

    @InjectMocks
    private ProductController productController;


    /*@Test
    public void givenProductCreationRequestThenRespondWithStatusCreated() {
        final var createProductRequestMock = Mockito.mock(CreateProductRequest.class);
        final var productMock = Mockito.mock(Product.class);

        Mockito.when(useCase.createProduct(productMock, gateway))
                .thenReturn(productMock);

        given()
                .port(port)
                .body(createProductRequestMock)
                .when()
                .post("/products")
                .then()
                .log().ifValidationFails()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(JSON);

    }*/
}

