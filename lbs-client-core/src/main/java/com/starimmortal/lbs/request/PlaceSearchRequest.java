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
 * 地点搜索请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchRequest extends BaseRequest<PlaceSearchResponse> {

	/**
	 * 搜索关键字，长度最大96个字节，注：keyword仅支持检索一个。（API采用UTF-8字符编码，1个英文字符占用1个字节，1个中文字符占3个字节）
	 */
	private String keyword;

	/**
	 * 筛选条件（分类词数量建议不超过5个，支持设置分类编码（支持的分类请参考：<a href=
	 * "https://lbs.qq.com/service/webService/webServiceGuide/webServiceAppendix">POI分类表</a>））
	 * 1. 指定分类筛选，语句格式为：category=分类名1,分类名2 2. 排除指定分类，语句格式为：category<>分类名1,分类名2 3.
	 * 筛选有电话的地点：tel<>null
	 */
	private FilterRequest filter;

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
		return ApiUrlConstant.PLACE_SEARCH_URL;
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
