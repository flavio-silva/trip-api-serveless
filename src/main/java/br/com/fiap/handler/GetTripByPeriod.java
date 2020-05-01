package br.com.fiap.handler;

import br.com.fiap.repository.TripRepository;
import br.com.fiap.http.HandlerRequest;
import br.com.fiap.http.HandlerResponse;
import br.com.fiap.entity.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.ArrayList;
import java.util.List;

public class GetTripByPeriod implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {
		final String starts = request.getQueryStringParameters().get("start");
		final String ends = request.getQueryStringParameters().get("end");

		List<Trip> trips = this.repository.findByPeriod(starts, ends);

		if (trips == null) {
			trips = new ArrayList<>();
		}

		return new HandlerResponse()
				.setStatusCode(200)
				.setBody(trips);
	}
}