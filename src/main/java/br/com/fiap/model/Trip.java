package br.com.fiap.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamoDBTable(tableName = "trip")
public class Trip {

    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBRangeKey(attributeName = "date")
    private String date;

    @DynamoDBAttribute(attributeName = "country")
    private String country;

    @DynamoDBAttribute(attributeName = "city")
    private String city;

    @DynamoDBAttribute(attributeName = "urlPhotos")
    private String urlPhotos;
}
