package br.com.fiap.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HandlerRequest {

	private String body;
	private String path;
	private Map<String, String> pathParameters;
	private Map<String, String> queryStringParameters;

}
