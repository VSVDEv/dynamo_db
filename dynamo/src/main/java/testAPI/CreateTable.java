package testAPI;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

public class CreateTable {
/*
    private static AmazonDynamoDB getAmazonDynamoDBClient() {
        //Create endpoint configuration which points to the Edge service (running on http://localhost:4566)
        AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration("http://localhost:4566",
                Regions.EU_WEST_1.getName());

        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(endpointConfig)
                .build();
    }

    private static CreateTableRequest getCreateTableRequest(){
        return new CreateTableRequest()
                .withAttributeDefinitions(new AttributeDefinition("Name", ScalarAttributeType.S))
                .withKeySchema(new KeySchemaElement("Name", KeyType.HASH))
                .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L))
                .withTableName("MyTable");
    }
    public static void main(String[] args) {
        final AmazonDynamoDB amazonDynamoDBClient = getAmazonDynamoDBClient();
        final CreateTableRequest request = getCreateTableRequest();
        try {
            CreateTableResult result = amazonDynamoDBClient.createTable(request);
            System.out.println(result.getTableDescription().getTableName());
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

 */


    public static void main(String[] args) {
        //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

        // Explicitly set the region
        // AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        //     .withRegion(Regions.US_WEST_2)
        //     .build();



        // Use a DynamoDB Local endpoint
         AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
             .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us"))
             .build();



         DynamoDB dynamoDB = new DynamoDB(client);

        String tableName = "Movies";

        try {
            System.out.println("Attempting to create table; please wait...");
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition key
                            new KeySchemaElement("title", KeyType.RANGE)), // Sort key
                    Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
                            new AttributeDefinition("title", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L, 10L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        }
        catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
        }

    }


}
