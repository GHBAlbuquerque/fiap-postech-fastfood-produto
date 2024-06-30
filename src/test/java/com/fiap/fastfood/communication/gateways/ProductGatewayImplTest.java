package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.interfaces.datasources.ProductRepository;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import com.fiap.fastfood.external.orm.ProductORM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductGatewayImplTest {

    @Mock
    private ProductRepository repositoryMock;

    @InjectMocks
    private ProductGatewayImpl productGateway;

    @Test
    void createProductTest() {
        final var ormMock = createMockProductORM();
        final var productMock = createMockProduct();

        Mockito.when(repositoryMock.save(any()))
                .thenReturn(ormMock);

        final var result = productGateway.saveProduct(productMock);

        Assertions.assertNotNull(result);
    }

    @Test
    void updateProductTest() {
        final var ormMock = createMockProductORM();
        final var productMock = createMockProduct();

        Mockito.when(repositoryMock.findById(productMock.getId()))
                .thenReturn(Optional.of(ormMock));

        Mockito.when(repositoryMock.save(any()))
                .thenReturn(ormMock);

        final var result = productGateway.updateProduct(productMock.getId(), productMock);

        Assertions.assertNotNull(result);
    }

    @Test
    void deleteProductTest() {
        final var productId = "productId";

        Assertions.assertDoesNotThrow(
                () -> productGateway.deleteProduct(productId)
        );
    }

    @Test
    void findProductsTest() {
        final var ormMock = createMockProductORM();
        final var pageRequest = PageRequest.of(0, 10);
        final var page = new PageImpl<>(List.of(ormMock));

        Mockito.when(repositoryMock.findAllByType(
                        ProductTypeEnum.SANDWICH.name(),
                        pageRequest))
                .thenReturn(page);

        final var result = productGateway.findProducts(ProductTypeEnum.SANDWICH, 0, 10);

        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void findByIdAndTypeTest() {
        final var ormMock = createMockProductORM();
        final var productMock = createMockProduct();
        final var productId = "productId";
        final var productType = "SANDWICH";

        Mockito.when(repositoryMock.findByIdAndType(productId, productType))
                .thenReturn(Optional.ofNullable(ormMock));

        final var result = productGateway.findByIdAndType(productId, productType);

        Assertions.assertNotNull(result);
    }


    private Product createMockProduct() {
        return Product.builder()
                .name("name")
                .price(BigDecimal.ONE)
                .description("description")
                .type(ProductTypeEnum.SANDWICH)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    private ProductORM createMockProductORM() {
        return ProductORM.builder()
                .id("id")
                .name("name")
                .price(BigDecimal.ONE)
                .description("description")
                .type(ProductTypeEnum.SANDWICH.name())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }
}
