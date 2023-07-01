package com.starimmortal.lbs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 矩形范围（屏幕视野内）搜索边界范围请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RectangleBoundaryRequest {

	/**
	 * 左下角（西南）纬度
	 */
	private Double southwestLat;

	/**
	 * 左下角（西南）经度
	 */
	private Double southwestLng;

	/**
	 * 右上角（东北）纬度
	 */
	private Double northeastLat;

	/**
	 * 右上角（东北）经度
	 */
	private Double northeastLng;

	@Override
	public String toString() {
		return "rectangle(" + this.southwestLat + "," + this.southwestLng + "," + this.northeastLat + ","
				+ this.northeastLng + ")";
	}

}
