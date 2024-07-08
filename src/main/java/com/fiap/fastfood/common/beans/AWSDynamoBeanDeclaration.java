package com.fiap.fastfood.common.beans;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AWSDynamoBeanDeclaration {

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @Value("${amazonProperties.sessionToken}")
    private String sessionToken;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        final var awsCredentials = new BasicSessionCredentials(accessKey, secretKey, sessionToken);

        return AmazonDynamoDBClient.builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(String.valueOf(Region.US_EAST_1))
                .build();
    }


}