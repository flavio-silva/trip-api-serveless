package br.com.fiap.dao;

import br.com.fiap.model.Trip;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TripRepository {

    private static DynamoDBMapper mapper = DynamoDbManager.mapper();

    public List<Trip> findByPeriod(String start, String end) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":start", new AttributeValue().withS(start));
        eav.put(":end", new AttributeValue().withS(end));

        Map<String, String> expression = new HashMap<>();
        expression.put("#date", "date");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("#date between :start and :end")
                .withExpressionAttributeValues(eav)
                .withExpressionAttributeNames(expression);

        return mapper.scan(Trip.class, scanExpression);
    }

    public Trip findById(String id) {

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":id", new AttributeValue().withS(id));

        DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(eav);

        if (!mapper.query(Trip.class, queryExpression).isEmpty()) {
            return mapper.query(Trip.class, queryExpression).get(0);
        }

        return null;
    }

    public Trip save(Trip trip) {
        UUID uuid = UUID.randomUUID();
        trip.setId(uuid.toString());

        mapper.save(trip);
        return trip;
    }
}
