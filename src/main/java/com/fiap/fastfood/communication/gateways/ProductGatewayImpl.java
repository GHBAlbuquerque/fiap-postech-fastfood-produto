package com.fiap.fastfood.communication.gateways;

import com.fiap.fastfood.common.builders.ProductBuilder;
import com.fiap.fastfood.common.interfaces.datasources.ProductRepository;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import com.fiap.fastfood.external.orm.ProductORM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository repository;

    public ProductGatewayImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        final var orm = ProductBuilder.fromDomainToOrm(product);
        final var result = repository.save(orm);
        return ProductBuilder.fromOrmToDomain(result);
    }

    @Override
    public Product updateProduct(String id, Product product) {
        final var existsProduct = repository.findById(id);

        if (existsProduct.isEmpty()) return null;

        final var updatedProduct = ProductBuilder.fromORMtoNewORM(existsProduct.get());

        final var savedProduct = repository.save(updatedProduct);

        return ProductBuilder.fromOrmToDomain(savedProduct);
    }

    @Override
    public Product findById(String id, String name) {
        final var optional = repository.findByIdAndName(id, name);

        if (optional.isPresent())
            return ProductBuilder.fromOrmToDomain(optional.get());

        return null;
    }

    @Override
    public Page<Product> findProducts(ProductTypeEnum type,
                                      Integer page,
                                      Integer size) {
        final Page<ProductORM> orms;
        final var pageRequest = PageRequest.of(page, size);

        if (type != null) {
            orms = repository.findAllByType(type.toString(), pageRequest);
        } else {
            orms = repository.findAll(pageRequest);
        }

        return orms.map(ProductBuilder::fromOrmToDomain);
    }
}
