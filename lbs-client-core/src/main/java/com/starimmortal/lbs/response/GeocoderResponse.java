package com.starimmortal.lbs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 地址解析（地址转坐标）响应结果类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GeocoderResponse extends BaseResponse {

	/**
	 * 地址解析（地址转坐标）结果
	 */
	private ResultDTO result;

	@Data
	public static class ResultDTO {

		/**
		 * 最终用于坐标解析的地址或地点名称
		 */
		private String title;

		/**
		 * 解析到的坐标（GCJ02坐标系）
		 */
		private LocationDTO location;

		/**
		 * 行政区划信息
		 */
		@JsonProperty("ad_info")
		private AdInfoDTO adInfo;

		/**
		 * 解析后的地址部件
		 */
		@JsonProperty("address_components")
		private AddressComponentsDTO addressComponents;

		/**
		 * 即将下线，由reliability代替
		 */
		private Double similarity;

		/**
		 * 即将下线，由level代替
		 */
		private Integer deviation;

		/**
		 * 可信度参考：值范围 1 <低可信> - 10 <高可信>
		 * <p>
		 * 我们根据用户输入地址的准确程度，在解析过程中，将解析结果的可信度(质量)，由低到高，分为1 -
		 * 10级，该值>=7时，解析结果较为准确，<7时，会存各类不可靠因素，开发者可根据自己的实际使用场景，对于解析质量的实际要求，进行参考。
		 */
		private Integer reliability;

		/**
		 * 解析精度级别，分为11个级别，一般>=9即可采用（定位到点，精度较高） 也可根据实际业务需求自行调整，完整取值表见下文。
		 */
		private Integer level;

		@Data
		public static class LocationDTO {

			/**
			 * 纬度
			 */
			private Double lng;

			/**
			 * 经度
			 */
			private Double lat;

		}

		@Data
		public static class AdInfoDTO {

			/**
			 * 行政区划代码，规则详见：<a href=
			 * "https://lbs.qq.com/service/webService/webServiceGuide/webServiceDistrict#6">行政区划代码说明</a>
			 */
			private String adcode;

		}

		@Data
		public static class AddressComponentsDTO {

			/**
			 * 省
			 */
			private String province;

			/**
			 * 市，如果当前城市为省直辖县级区划，city与district字段均会返回此城市
			 * <p>
			 * 注：省直辖县级区划adcode第3和第4位分别为9、0，如济源市adcode为419001
			 */
			private String city;

			/**
			 * 区，可能为空字串
			 */
			private String district;

			/**
			 * 街道/道路，可能为空字串
			 */
			private String street;

			/**
			 * 门牌，可能为空字串
			 */
			@JsonProperty("street_number")
			private String streetNumber;

		}

	}

}
