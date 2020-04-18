package br.com.fiap.dao;

import br.com.fiap.model.Trip;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRepository {

    private static final DynamoDBMapper mapper = DynamoDbManager.mapper();

    public List<Trip> findByPeriod(String starts, String ends) {
        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":starts", new AttributeValue().withS(starts));
        eav.put(":ends", new AttributeValue().withS(ends));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("dateTrip between :starts and :ends")
                .withExpressionAttributeValues(eav);

        return mapper.query(Trip.class, queryExpression);

    }

    public List<Trip> findById(String id) {

        final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":id", new AttributeValue().withS(id));

        final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
                .withKeyConditionExpression("id = :id")
                .withExpressionAttributeValues(eav);

        final List<Trip> trips = mapper.query(Trip.class, queryExpression);

        return trips;
    }

    public Trip save(Trip trip) {
        mapper.save(trip);
        return trip;
    }
}
