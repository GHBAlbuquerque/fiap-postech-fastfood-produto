package com.fiap.fastfood.common.interfaces.gateways;

import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductGateway {
    void deleteProduct(String id);

    Page<Product> findProducts(ProductTypeEnum type, Integer page, Integer size);

    Product saveProduct(Product product);

    Product updateProduct(String id, Product product);

    Product findById(String id, String name);
}
