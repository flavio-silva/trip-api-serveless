package br.com.fiap.handler;

import br.com.fiap.repository.TripRepository;
import br.com.fiap.http.HandlerRequest;
import br.com.fiap.http.HandlerResponse;
import br.com.fiap.entity.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetTripById implements RequestHandler<HandlerRequest, HandlerResponse> {

	private TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		String id = request.getPathParameters().get("id");

		Trip trip = this.repository.findById(id);

		if (trip == null) {
			return new HandlerResponse()
					.setStatusCode(404);

		}

		return new HandlerResponse()
				.setStatusCode(200)
				.setBody(trip);
	}
}