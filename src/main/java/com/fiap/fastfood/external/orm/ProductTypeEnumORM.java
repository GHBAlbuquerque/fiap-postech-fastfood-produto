package com.fiap.fastfood.external.orm;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public enum ProductTypeEnumORM {
    SANDWICH, SIDE_DISH, DRINK
}
