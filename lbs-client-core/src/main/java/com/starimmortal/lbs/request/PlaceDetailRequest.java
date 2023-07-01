package com.starimmortal.lbs.request;

import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.PlaceSearchResponse;
import lombok.*;
import org.springframework.http.HttpMethod;

/**
 * 地点搜索ID查询请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDetailRequest extends BaseRequest<PlaceSearchResponse> {

	/**
	 * 腾讯地图POI（地点）唯一标识，支持多poiid检索，最大支持10个，用英文逗号分隔
	 */
	private String id;

	@Override
	public String getUrl() {
		return ApiUrlConstant.PLACE_DETAIL_URL;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	@Override
	public Class<PlaceSearchResponse> getResponseType() {
		return PlaceSearchResponse.class;
	}

}
