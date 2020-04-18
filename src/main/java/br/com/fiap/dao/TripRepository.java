package br.com.fiap.dao;

import br.com.fiap.model.Trip;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class TripRepository {

    private static final DynamoDBMapper mapper = DynamoDbManager.mapper();

    public Trip save(Trip trip) {
        return trip;
    }

}
