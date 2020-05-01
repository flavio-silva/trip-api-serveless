package br.com.fiap.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getCountry());
        builder.append("-");
        builder.append(getCity());
        builder.append("-");
        builder.append(getDate());
        builder.append("-");

        int min = 100000;
        int max = 999999;
        int random_int = (int)(Math.random() * (max - min + 1) + min);

        builder.append(random_int);

        return builder.toString();
    }
}
