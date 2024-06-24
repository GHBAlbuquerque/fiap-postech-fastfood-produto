package com.fiap.fastfood.common.interfaces.usecase;

import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;

public interface ProductUseCase {

    Product createProduct(Product product, ProductGateway productGateway);

    Product updateProduct(String id, Product product, ProductGateway productGateway) throws EntityNotFoundException;

    void deleteProduct(String id, ProductGateway productGateway);

    Page<Product> findProducts(@Nullable ProductTypeEnum type, Integer page, Integer size, ProductGateway productGateway);

    Product findByIdAndType(String id, String name, ProductGateway productGateway) throws EntityNotFoundException;
}
