package br.com.fiap.handler;

import br.com.fiap.repository.TripRepository;
import br.com.fiap.http.HandlerRequest;
import br.com.fiap.http.HandlerResponse;
import br.com.fiap.entity.Trip;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetTripByPeriod implements RequestHandler<HandlerRequest, HandlerResponse> {

	private TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {
		String start = request.getQueryStringParameters().get("start");
		String end = request.getQueryStringParameters().get("end");
		try {
			if (start == null || end == null) {
				throw new IOException("\"{\\\"errors\\\": \\\"Missing query parameters\\\"}\"");
			}

			String dateRegex = "^[0-9]{4}/[0-9]{2}/[0-9]{2}$";
			if (!end.matches(dateRegex) || !start.matches(dateRegex)) {
				throw new IOException("{ \"errors\": \"The date field is invalid.\" }");
			}

			List<Trip> trips = this.repository.findByPeriod(start, end);

			trips = trips == null ? new ArrayList<>() : trips;

			return new HandlerResponse()
					.setStatusCode(200)
					.setBody(trips);
		} catch (IOException exception) {
			return new HandlerResponse()
			.setStatusCode(400)
			.setBody(exception.getMessage());
		}
	}
}