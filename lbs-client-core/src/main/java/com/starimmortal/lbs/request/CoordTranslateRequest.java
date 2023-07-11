package com.starimmortal.lbs.request;

import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.CoordTranslateResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 坐标转换请求类
 *
 * @author william@StarImmortal
 * @date 2023/07/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordTranslateRequest extends BaseRequest<CoordTranslateResponse> {

	/**
	 * 预转换的坐标，支持批量转换， 格式：纬度前，经度后，纬度和经度之间用",“分隔，每组坐标之间使用”;"分隔； 批量支持坐标个数以HTTP GET方法请求上限为准
	 */
	private String locations;

	/**
	 * 输入的locations的坐标类型，可选值：1-GPS坐标；2-sogou经纬度；3-baidu经纬度；4-mapbar经纬度；6-sogou墨卡托
	 */
	private Integer type;

	@Override
	public String getUrl() {
		return ApiUrlConstant.COORD_TRANSLATE_URL;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	@Override
	public Class<CoordTranslateResponse> getResponseType() {
		return CoordTranslateResponse.class;
	}

	public void setLocations(List<LocationRequest> locations) {
		this.locations = locations.stream().map(LocationRequest::toString).collect(Collectors.joining(";"));
	}

}
