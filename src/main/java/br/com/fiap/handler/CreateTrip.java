package br.com.fiap.handler;

import br.com.fiap.http.HandlerRequest;
import br.com.fiap.http.HandlerResponse;
import br.com.fiap.entity.Trip;
import br.com.fiap.service.TripService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class CreateTrip implements RequestHandler<HandlerRequest, HandlerResponse> {
    private TripService service = new TripService();

    @Override
    public HandlerResponse handleRequest(HandlerRequest request, Context context) {
        try {
            Trip trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
            return new HandlerResponse()
                    .setBody(service.save(trip))
                    .setStatusCode(201);
        } catch (IOException exception) {
            return new HandlerResponse()
                    .setBody("Cannot create a trip")
                    .setStatusCode(400);
        }
    }
}
