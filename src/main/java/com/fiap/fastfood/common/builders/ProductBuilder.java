package com.fiap.fastfood.common.builders;

import com.fiap.fastfood.common.dto.request.CreateProductRequest;
import com.fiap.fastfood.common.dto.request.UpdateProductRequest;
import com.fiap.fastfood.common.dto.response.FullProductResponse;
import com.fiap.fastfood.common.utils.TimeConverter;
import com.fiap.fastfood.core.entity.Product;
import com.fiap.fastfood.core.entity.ProductTypeEnum;
import com.fiap.fastfood.external.orm.ProductORM;

import java.time.LocalDateTime;

public class ProductBuilder {

    public static Product toDomain(CreateProductRequest request) {
        return buildCreateProductFromRequest(request);
    }

    public static Product toDomain(UpdateProductRequest request, LocalDateTime timestamp) {
        return buildUpdateProductFromRequest(request, LocalDateTime.now());
    }

    public static FullProductResponse toResponse(Product product) {
        return FullProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .type(product.getType().toString())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private static <BaseProductRequest> Product buildCreateProductFromRequest(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .type(ProductTypeEnum.valueOf(request.getType()))
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();
    }

    private static <BaseProductRequest> Product buildUpdateProductFromRequest(UpdateProductRequest request, LocalDateTime updatedAt) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .type(ProductTypeEnum.valueOf(request.getType()))
                .updatedAt(updatedAt)
                .build();
    }

    public static Product fromOrmToDomain(ProductORM orm) {
        return Product.builder()
                .id(orm.getId())
                .name(orm.getName())
                .price(orm.getPrice())
                .description(orm.getDescription())
                .type(ProductTypeEnum.valueOf(
                        orm.getType())
                )
                .createdAt(
                        TimeConverter.convertToLocalDateTimeViaInstant(orm.getCreatedAt())
                )
                .updatedAt(
                        TimeConverter.convertToLocalDateTimeViaInstant(orm.getUpdatedAt())
                )
                .build();
    }

    public static ProductORM fromDomainToOrm(Product product) {
        final var productORM = ProductORM.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .type(product.getType().toString())
                .build();

        if (product.getCreatedAt() != null)
            productORM.setCreatedAt(TimeConverter.convertToDateViaInstant(product.getCreatedAt()));

        if (product.getUpdatedAt() != null)
            productORM.setUpdatedAt(TimeConverter.convertToDateViaInstant(product.getUpdatedAt()));

        return productORM;
    }

    public static ProductORM fromORMtoNewORM(ProductORM orm) {
        return ProductORM.builder()
                .id(orm.getId())
                .name(orm.getName())
                .price(orm.getPrice())
                .description(orm.getDescription())
                .type(orm.getType())
                .createdAt(orm.getCreatedAt())
                .updatedAt(orm.getUpdatedAt())
                .build();
    }
}
