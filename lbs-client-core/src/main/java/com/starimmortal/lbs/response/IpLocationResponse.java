package com.starimmortal.lbs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IP定位响应结果类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class IpLocationResponse extends BaseResponse {

	/**
	 * IP定位结果
	 */
	private ResultDTO result;

	@Data
	public static class ResultDTO {

		/**
		 * 用于定位的IP地址
		 */
		private String ip;

		/**
		 * 定位坐标。注：IP定位服务精确到市级，该位置为IP地址所属的行政区划政府坐标。
		 */
		private LocationDTO location;

		/**
		 * 定位行政区划信息
		 */
		@JsonProperty(value = "ad_info")
		private AdInfoDTO adInfo;

		@Data
		public static class LocationDTO {

			/**
			 * 纬度
			 */
			private Double lat;

			/**
			 * 经度
			 */
			private Double lng;

		}

		@Data
		public static class AdInfoDTO {

			/**
			 * 国家
			 */
			private String nation;

			/**
			 * 国家代码（ISO3166标准3位数字码）
			 */
			@JsonProperty(value = "nation_code")
			private String nationCode;

			/**
			 * 省
			 */
			private String province;

			/**
			 * 市
			 */
			private String city;

			/**
			 * 区
			 */
			private String district;

			/**
			 * 行政区划代码
			 */
			private Integer adcode;

		}

	}

}
