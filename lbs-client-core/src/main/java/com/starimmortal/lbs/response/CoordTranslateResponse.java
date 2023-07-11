package com.starimmortal.lbs.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 坐标转换响应结果类
 *
 * @author william@StarImmortal
 * @date 2023/07/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CoordTranslateResponse extends BaseResponse {

	/**
	 * 坐标转换结果，转换后的坐标顺序与输入顺序一致
	 */
	private List<LocationDTO> locations;

	@Data
	private static class LocationDTO {

		/**
		 * 纬度
		 */
		private Double lng;

		/**
		 * 经度
		 */
		private Double lat;

	}

}
