package com.fiap.fastfood.common.interfaces.datasources;

import com.fiap.fastfood.external.orm.ProductORM;
import com.fiap.fastfood.external.orm.ProductTypeEnumORM;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductORM, String> {

    List<ProductORM> findByType(ProductTypeEnumORM type);
}
