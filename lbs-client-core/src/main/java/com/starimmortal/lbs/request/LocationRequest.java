package com.starimmortal.lbs.request;

import lombok.*;

/**
 * 经纬度（GCJ02坐标系）请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest {

	/**
	 * 纬度
	 */
	private Double lat;

	/**
	 * 经度
	 */
	private Double lng;

	@Override
	public String toString() {
		return lat + "," + lng;
	}

}
