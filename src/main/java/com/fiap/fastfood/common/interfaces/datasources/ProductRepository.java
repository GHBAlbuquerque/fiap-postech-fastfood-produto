package com.fiap.fastfood.common.interfaces.datasources;

import com.fiap.fastfood.external.orm.ProductORM;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@EnableScan
@EnableScanCount
public interface ProductRepository extends PagingAndSortingRepository<ProductORM, String>, CrudRepository<ProductORM, String> {

    Page<ProductORM> findAllByType(String type, Pageable pageable);

    Optional<ProductORM> findByIdAndType(String id, String type);
}
