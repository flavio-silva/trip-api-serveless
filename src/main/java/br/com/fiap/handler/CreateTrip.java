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
        Trip trip;
        try {
            trip = new ObjectMapper().readValue(request.getBody(), Trip.class);
        } catch (IOException exception) {
            return HandlerResponse
                    .builder()
                    .setStatusCode(400)
                    .setRawBody("Cannot create a trip ")
                    .build();
        }

        Trip savedTrip = service.save(trip);

        return HandlerResponse
                .builder()
                .setStatusCode(201)
                .setObjectBody(savedTrip)
                .build();
    }
}
