package br.com.fiap.handler;

import br.com.fiap.model.HandlerRequest;
import br.com.fiap.model.HandlerResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreateTrip implements RequestHandler<HandlerRequest, HandlerResponse> {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {
        System.out.println(request.getBody());
        return HandlerResponse.builder().setStatusCode(201).build();
    }
}
