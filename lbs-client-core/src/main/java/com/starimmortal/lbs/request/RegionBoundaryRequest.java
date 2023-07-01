package com.starimmortal.lbs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 城市/区域搜索边界范围请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionBoundaryRequest {

	/**
	 * 城市名称
	 */
	private String cityName;

	/**
	 * 纬度
	 */
	private Double lat;

	/**
	 * 经度
	 */
	private Double lng;

	/**
	 * 当前范围无结果时，是否自动扩大范围 0：仅在当前城市搜索 1：（默认）若当前城市搜索无结果，则自动扩大范围
	 * 2：限制在当前区/县范围搜索，无结果时不自动扩大范围（仅在传入city_name为区级或区级行政区划代码时有效）
	 */
	private Integer autoExtend;

	@Override
	public String toString() {
		StringBuilder boundaryBuilder = new StringBuilder();
		boundaryBuilder.append("region(").append(this.cityName);
		if (Objects.nonNull(this.autoExtend)) {
			boundaryBuilder.append(",").append(this.autoExtend);
		}
		if (Objects.nonNull(this.lat) && Objects.nonNull(this.lng)) {
			boundaryBuilder.append(",").append(this.lat).append(",").append(this.lng);
		}
		boundaryBuilder.append(")");
		return boundaryBuilder.toString();
	}

}
