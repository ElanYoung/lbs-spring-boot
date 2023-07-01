package com.starimmortal.lbs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 周边搜索边界范围请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearbyBoundaryRequest {

	/**
	 * 纬度
	 */
	private Double lat;

	/**
	 * 经度
	 */
	private Double lng;

	/**
	 * 搜索半径，单位：米，取值范围：10到1000
	 */
	private Integer radius;

	/**
	 * 当前范围无结果时，是否自动扩大范围 0：不扩大 1：（默认）自动扩大范围（依次按照按1公里、2公里、5公里，最大到全城市范围搜索）
	 */
	private Integer autoExtend;

	@Override
	public String toString() {
		StringBuilder boundaryBuilder = new StringBuilder();
		boundaryBuilder.append("nearby(").append(this.lat).append(",").append(this.lng).append(",").append(this.radius);
		if (Objects.nonNull(this.autoExtend)) {
			boundaryBuilder.append(",").append(this.autoExtend);
		}
		boundaryBuilder.append(")");
		return boundaryBuilder.toString();
	}

}
