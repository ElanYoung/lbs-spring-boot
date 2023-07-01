package com.starimmortal.lbs.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.ReverseGeocoderResponse;
import com.starimmortal.lbs.serializer.LbsRequestSerializer;
import lombok.*;
import org.springframework.http.HttpMethod;

/**
 * 逆地址解析（坐标位置描述）请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReverseGeocoderRequest extends BaseRequest<ReverseGeocoderResponse> {

	/**
	 * 经纬度（GCJ02坐标系），格式：location=lat<纬度>,lng<经度>
	 */
	@JsonSerialize(using = LbsRequestSerializer.class)
	private LocationRequest location;

	/**
	 * 是否返回子地点，如大厦停车场、出入口等取值：0：不返回（默认） 1：返回
	 */
	private Integer getSubpois;

	/**
	 * 周边POI（AOI）列表控制参数： 1.poi_options=address_format=short（返回短地址，缺省时返回长地址）
	 * 2.poi_options=radius=5000（半径，取值范围 1-5000（米）） 3.poi_options=policy=1/2/3/4/5（控制返回场景）
	 * policy=1（默认）：以地标+主要的路+近距离POI为主，着力描述当前位置； policy=2：到家场景：筛选合适收货的POI，并会细化收货地址，精确到楼栋；
	 * policy=3：出行场景：过滤掉车辆不易到达的POI(如一些景区内POI)，增加道路出入口、交叉口、大区域出入口类POI，排序会根据真实API大用户的用户点击自动优化。
	 * policy=4：社交签到场景，针对用户签到的热门 地点进行优先排序。 policy=5：位置共享场景，用户经常用于发送位置、位置分享等场景的热门地点优先排序 4
	 * 注：policy=1/2/3最多返回10条周边POI，policy=4/5最多返回20条，
	 */
	private String poiOptions;

	@Override
	public String getUrl() {
		return ApiUrlConstant.GEOCODER_URL;
	}

	@Override
	public HttpMethod getHttpMethod() {
		return HttpMethod.GET;
	}

	@Override
	public Class<ReverseGeocoderResponse> getResponseType() {
		return ReverseGeocoderResponse.class;
	}

}
