package com.starimmortal.lbs.request;

import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.GeocoderResponse;
import lombok.*;
import org.springframework.http.HttpMethod;

/**
 * 地址解析（地址转坐标）请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocoderRequest extends BaseRequest<GeocoderResponse> {

	/**
	 * 地址（注：地址中请包含城市名称，以及需要对地址进行URL编码，否则会影响解析效果）
	 */
	private String address;

	@Override
	public String getUrl() {
		return ApiUrlConstant.GEOCODER_URL;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	@Override
	public Class<GeocoderResponse> getResponseType() {
		return GeocoderResponse.class;
	}

}
