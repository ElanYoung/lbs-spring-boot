package com.starimmortal.lbs.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 请求类转换指定格式
 *
 * @author william@StarImmortal
 * @date 2023/06/20
 */
public class LbsRequestSerializer<T> extends JsonSerializer<T> {

	@Override
	public void serialize(T request, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeString(request.toString());
	}

}
