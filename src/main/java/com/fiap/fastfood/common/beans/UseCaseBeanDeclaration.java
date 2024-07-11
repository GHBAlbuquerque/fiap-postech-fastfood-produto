package com.fiap.fastfood.common.beans;

import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import com.fiap.fastfood.core.usecase.ProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanDeclaration {

    @Bean
    public ProductUseCase productUseCase() {
        return new ProductUseCaseImpl();
    }
}
