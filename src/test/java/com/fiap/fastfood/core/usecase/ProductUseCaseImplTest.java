package com.fiap.fastfood.core.usecase;

import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductUseCaseImplTest {

    @InjectMocks
    ProductUseCaseImpl productUseCase;

    @Test
    void createProductTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productMock = Mockito.mock(Product.class);
        productMock.setId("productId");

        Mockito.when(gatewayMock.saveProduct(productMock))
                .thenReturn(productMock);

        final var result = productUseCase.createProduct(productMock, gatewayMock);

        Assertions.assertNotNull(result);
    }

    @Test
    void updateProductTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productMock = Mockito.mock(Product.class);
        final var productId = "productId";

        Mockito.when(gatewayMock.updateProduct(productId, productMock))
                .thenReturn(productMock);


        final var result = Assertions.assertDoesNotThrow(
                () -> productUseCase.updateProduct(productId, productMock, gatewayMock)
        );

        Assertions.assertNotNull(result);
    }

    @Test
    void updateProductErrorTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productMock = Mockito.mock(Product.class);
        final var productId = "productId";

        Mockito.when(gatewayMock.updateProduct(productId, productMock))
                .thenReturn(null);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productUseCase.updateProduct(productId, productMock, gatewayMock)
        );
    }

    @Test
    void deleteProductTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productId = "productId";

        Assertions.assertDoesNotThrow(
                () -> productUseCase.deleteProduct(productId, gatewayMock)
        );
    }

    @Test
    void findProductsTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productMock = Mockito.mock(Product.class);
        final var page = new PageImpl<>(List.of(productMock));

        Mockito.when(gatewayMock.findProducts(ProductTypeEnum.SANDWICH, 0, 10))
                .thenReturn(page);

        final var result = productUseCase.findProducts(ProductTypeEnum.SANDWICH, 0, 10, gatewayMock);

        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void findByIdAndTypeTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productMock = Mockito.mock(Product.class);
        final var productId = "productId";
        final var productType = "SANDWICH";

        Mockito.when(gatewayMock.findByIdAndType(productId, productType))
                .thenReturn(productMock);

        final var result = Assertions.assertDoesNotThrow(
                () -> productUseCase.findByIdAndType(productId, productType, gatewayMock)
        );

        Assertions.assertNotNull(result);
    }

    @Test
    void findByIdAndTypeErrorTest() {
        final var gatewayMock = Mockito.mock(ProductGateway.class);
        final var productId = "productId";
        final var productType = "SANDWICH";

        Mockito.when(gatewayMock.findByIdAndType(productId, productType))
                .thenReturn(null);

        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productUseCase.findByIdAndType(productId, productType, gatewayMock)
        );

    }
}
