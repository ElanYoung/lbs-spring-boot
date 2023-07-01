package com.starimmortal.lbs.request;

import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.IpLocationResponse;
import lombok.*;
import org.springframework.http.HttpMethod;

/**
 * IP定位请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpLocationRequest extends BaseRequest<IpLocationResponse> {

	/**
	 * IP地址，缺省时会使用请求端IP
	 */
	private String ip;

	@Override
	public String getUrl() {
		return ApiUrlConstant.LOCATION_IP_URL;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	@Override
	public Class<IpLocationResponse> getResponseType() {
		return IpLocationResponse.class;
	}

}
