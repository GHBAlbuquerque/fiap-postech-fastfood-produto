package com.fiap.fastfood.common.beans;

import com.fiap.fastfood.common.interfaces.datasources.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GatewayBeanDeclarationTest {

    @InjectMocks
    private GatewayBeanDeclaration declaration;

    @Test
    void productGatewayTest() {
        final var mock = Mockito.mock(ProductRepository.class);

        final var result = declaration.productGateway(mock);

        Assertions.assertNotNull(result);
    }
}
