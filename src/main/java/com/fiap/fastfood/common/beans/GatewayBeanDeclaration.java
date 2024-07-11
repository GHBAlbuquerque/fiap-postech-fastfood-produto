package com.fiap.fastfood.common.beans;

import com.fiap.fastfood.common.interfaces.datasources.ProductRepository;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.communication.gateways.ProductGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayBeanDeclaration {

    @Bean
    public ProductGateway productGateway(ProductRepository repository) {
        return new ProductGatewayImpl(repository);
    }

}
