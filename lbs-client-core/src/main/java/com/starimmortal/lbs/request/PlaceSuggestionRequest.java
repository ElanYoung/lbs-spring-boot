package com.starimmortal.lbs.request;

import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.response.PlaceSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpMethod;

/**
 * 关键词输入提示请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSuggestionRequest extends BaseRequest<PlaceSearchResponse> {

	/**
	 * 搜索关键字，长度最大96个字节，注：keyword仅支持检索一个。（API采用UTF-8字符编码，1个英文字符占用1个字节，1个中文字符占3个字节）
	 */
	private String keyword;

	/**
	 * 限制城市范围： 根据城市名称限制地域范围， 如，仅获取“广州市”范围内的提示内容； 缺省时侧进行全国范围搜索；
	 */
	private String region;

	/**
	 * 0：（默认）当前城市无结果时，自动扩大范围到全国匹配 1：固定在当前城市
	 */
	private Integer regionFix;

	/**
	 * 是否返回子地点，如大厦停车场、出入口等取值： 0：不返回（默认） 1：返回
	 */
	private Integer getSubpois;

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
		return ApiUrlConstant.PLACE_SUGGESTION_URL;
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
