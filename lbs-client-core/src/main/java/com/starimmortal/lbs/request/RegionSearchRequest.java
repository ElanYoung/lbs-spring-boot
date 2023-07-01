package com.starimmortal.lbs.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.starimmortal.lbs.serializer.LbsRequestSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 城市/区域搜索请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RegionSearchRequest extends PlaceSearchRequest {

	/**
	 * 格式：boundary=region(city_name [,auto_extend][,lat,lng])，子参数： 1. city_name：检索城市名称，
	 * 如北京市，同时支持adcode（行政区划代码，可精确到区县级），如130681 2. auto_extend：[可选]当前范围无结果时，是否自动扩大范围，取值：0
	 * 仅在当前城市搜索；1 [默认] 若当前城市搜索无结果，则自动扩大范围；2
	 * 限制在当前区/县范围搜索，无结果时不自动扩大范围（仅在传入city_name为区级或区级行政区划代码时有效）。 3. lat,lng：[可选]
	 * 当keyword使用酒店、超市等泛分类关键词时，这类场景大多倾向于搜索附近，传入此经纬度，搜索结果会优先就近地点，体验更优。格式顺序为纬度在前，经度在后
	 */
	@JsonSerialize(using = LbsRequestSerializer.class)
	private RegionBoundaryRequest boundary;

	/**
	 * 是否返回子地点，如大厦停车场、出入口等取值： 0：不返回（默认） 1：返回
	 */
	private Integer getSubpois;

}
