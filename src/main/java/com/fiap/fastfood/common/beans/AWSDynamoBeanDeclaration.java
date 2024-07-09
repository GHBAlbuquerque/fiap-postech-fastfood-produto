package com.fiap.fastfood.common.beans;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class AWSDynamoBeanDeclaration {

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        /*return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();*/

        return AmazonDynamoDBClient.builder()
                .withRegion(String.valueOf(Region.US_EAST_1))
                .build();
    }


//    @Value("${aws.accessKey}")
//    private String accessKey;
//
//    @Value("${aws.secretKey}")
//    private String secretKey;
//
//    @Value("${aws.sessionToken}")
//    private String sessionToken;
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        final var awsCredentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);
//
//        return AmazonDynamoDBClient.builder()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
//                .withRegion(String.valueOf(Region.US_EAST_1))
//                .build();
//    }


}