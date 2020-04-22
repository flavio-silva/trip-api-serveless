package br.com.fiap.handler;

import br.com.fiap.model.HandlerRequest;
import br.com.fiap.model.HandlerResponse;
import br.com.fiap.model.Trip;
import br.com.fiap.service.TripService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class CreateTrip implements RequestHandler<HandlerRequest, HandlerResponse> {
    private TripService service = new TripService();

    @Override
    public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {
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
        context.getLogger().log("Creating a new trip");
        Trip savedTrip = service.save(trip);

        return HandlerResponse
                .builder()
                .setStatusCode(201)
                .setObjectBody(savedTrip)
                .build();
    }
}
