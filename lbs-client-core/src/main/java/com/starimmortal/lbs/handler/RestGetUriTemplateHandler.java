package com.starimmortal.lbs.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;
import java.util.Map;

/**
 * 解决需编写占位符问题
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
public class RestGetUriTemplateHandler implements UriTemplateHandler {

	private final UriTemplateHandler uriTemplateHandler = new DefaultUriBuilderFactory();

	@NonNull
	@Override
	public URI expand(@NonNull String uriTemplate, @NonNull Map<String, ?> uriVariables) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uriTemplate);
		for (Map.Entry<String, ?> entry : uriVariables.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		String uriString = builder.toUriString();
		return URI.create(uriString);
	}

	@NonNull
	@Override
	public URI expand(@NonNull String uriTemplate, @NonNull Object... uriVariables) {
		return URI.create(uriTemplateHandler.expand(uriTemplate, uriVariables).toString());
	}

}
