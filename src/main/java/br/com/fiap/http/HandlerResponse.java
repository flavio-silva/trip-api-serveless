package br.com.fiap.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import lombok.Data;

@Data
public class HandlerResponse {

	private static ObjectMapper objectMapper = new ObjectMapper();

	private int statusCode = 200;
	private String body;
	private Map<String, String> headers = Collections.emptyMap();
	private boolean isBase64Encoded;

	// API Gateway expects the property to be called "isBase64Encoded" => isIs
	public boolean isIsBase64Encoded() {
		return isBase64Encoded;
	}

	public HandlerResponse setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public HandlerResponse setBody(String body) {
		this.body = body;
		return this;
	}

	public HandlerResponse setBody(Object body) {

		try {
			this.body = objectMapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return this;
	}

	public HandlerResponse setBody(byte[] body) {
		this.body = new String(Base64.getEncoder().encode(body), StandardCharsets.UTF_8);
		this.isBase64Encoded = true;
		return this;
	}
}