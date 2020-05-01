package br.com.fiap.handler;

import br.com.fiap.dao.TripRepository;
import br.com.fiap.model.HandlerRequest;
import br.com.fiap.model.HandlerResponse;
import br.com.fiap.model.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetTripById implements RequestHandler<HandlerRequest, HandlerResponse> {

	private TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		String id = request.getPathParameters().get("id");

		Trip trip = this.repository.findById(id);

		if (trip == null) {
			return HandlerResponse
					.builder()
					.setStatusCode(404)
					.build();
		}

		return HandlerResponse.builder()
				.setStatusCode(200)
				.setObjectBody(trip)
				.build();
	}
}