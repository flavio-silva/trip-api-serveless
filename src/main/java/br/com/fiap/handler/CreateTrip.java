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

            if (trip.getCity() == null || trip.getCountry() == null || trip.getDate() == null) {
                throw new IOException("{ \"errors\": \"Missing body parameters.\" }");
            }

            if (!trip.getDate().matches("^[0-9]{4}/[0-9]{2}/[0-9]{2}$")) {
                throw new IOException("{ \"errors\": \"The date field is invalid.\" }");
            }

            return new HandlerResponse()
                    .setBody(service.save(trip))
                    .setStatusCode(201);
        } catch (IOException exception) {
            return new HandlerResponse()
                    .setBody(exception.getMessage())
                    .setStatusCode(400);
        }
    }
}
