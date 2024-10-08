package com.fiap.fastfood.core.usecase;

import com.fiap.fastfood.common.exceptions.custom.EntityNotFoundException;
import com.fiap.fastfood.common.interfaces.gateways.ProductGateway;
import com.fiap.fastfood.common.interfaces.usecase.ProductUseCase;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;

public class ProductUseCaseImpl implements ProductUseCase {

    private static final Logger logger = LogManager.getLogger(ProductUseCaseImpl.class);

    @Override
    public Product createProduct(Product product, ProductGateway productGateway) {
        final Product savedProduct = productGateway.saveProduct(product);
        logger.info("created product with success id = {}", savedProduct.getId());
        return savedProduct;
    }

    @Override
    public Product updateProduct(String id, Product product, ProductGateway productGateway) throws EntityNotFoundException {

        final var newProduct = productGateway.updateProduct(id, product);

        if (newProduct == null) {
            logger.error("not found product with id = {}", id);
            throw new EntityNotFoundException(
                    product.getId(),
                    "Product not found."
            );
        }

        logger.info("product updated successfully = {}", newProduct.getId());
        return newProduct;
    }

    @Override
    public void deleteProduct(String id, ProductGateway productGateway) {
        productGateway.deleteProduct(id);
        logger.info("product deleted successfully = {}", id);
    }

    @Override
    public Page<Product> findProducts(ProductTypeEnum type,
                                      Integer page,
                                      Integer size,
                                      ProductGateway productGateway) {
        return productGateway.findProducts(type, page, size);
    }

    @Override
    public Product findByIdAndType(String id, String type, ProductGateway productGateway) throws EntityNotFoundException {
        final var product = productGateway.findByIdAndType(id, type);

        if (product == null) {
            logger.error("not found product with id = {}", id);
            throw new EntityNotFoundException(
                    id,
                    "Product not found."
            );
        }

        return product;
    }

}
