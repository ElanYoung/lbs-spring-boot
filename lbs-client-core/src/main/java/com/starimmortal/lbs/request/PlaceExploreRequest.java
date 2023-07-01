package com.starimmortal.lbs.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.PlaceSearchResponse;
import com.starimmortal.lbs.serializer.LbsRequestSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpMethod;

/**
 * 周边推荐请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceExploreRequest extends BaseRequest<PlaceSearchResponse> {

	/**
	 * 格式：boundary=nearby(lat,lng,radius[, auto_extend])，子参数： 1.
	 * lat,lng：搜索中心点的经纬度，格式顺序为纬度在前，经度在后 2. radius：搜索半径，单位：米，取值范围：10到1000 3.
	 * auto_extend：[可选]
	 * 当前范围无结果时，是否自动扩大范围，取值：0（不扩大）；1（默认）自动扩大范围（依次按照按1公里、2公里、5公里，最大到全城市范围搜索）
	 */
	@JsonSerialize(using = LbsRequestSerializer.class)
	private NearbyBoundaryRequest boundary;

	/**
	 * 搜索策略，可选值： 1：[默认]地点签到场景，针对用户签到的热门 地点进行优先排序。 2：位置共享场景，用于发送位置、位置分享等场景的热门地点优先排序
	 */
	private Integer policy;

	/**
	 * 筛选条件（分类词数量建议不超过5个，支持设置分类编码（支持的分类请参考：<a href=
	 * "https://lbs.qq.com/service/webService/webServiceGuide/webServiceAppendix">POI分类表</a>））
	 * 1. 指定分类筛选，语句格式为：category=分类名1,分类名2 2. 排除指定分类，语句格式为：category<>分类名1,分类名2 3.
	 * 筛选有电话的地点：tel<>null
	 */
	private FilterRequest filter;

	/**
	 * 排序，支持按距离由近到远排序，取值：_distance 说明： 1. 周边搜索默认排序会综合考虑距离、权重等多方面因素 2.
	 * 设置按距离排序后则仅考虑距离远近，一些低权重的地点可能因距离近排在前面，导致体验下降
	 */
	private String orderby;

	/**
	 * 地址格式，可选值：short，返回不包含省市区的短地址（缺省侧为包含省市区的标准地址）
	 */
	private String addressFormat;

	/**
	 * 每页条目数，最大限制为20条，默认为10条
	 */
	private Integer pageSize;

	/**
	 * 第x页，默认第1页
	 */
	private Integer pageIndex;

	@Override
	public String getUrl() {
		return ApiUrlConstant.PLACE_EXPLORE_URL;
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
