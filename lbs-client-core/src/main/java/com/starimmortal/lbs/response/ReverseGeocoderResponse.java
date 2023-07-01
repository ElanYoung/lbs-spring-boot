package com.starimmortal.lbs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 逆地址解析（坐标位置描述）响应结果类
 *
 * @author william@StarImmortal
 * @date 2023/06/26
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class ReverseGeocoderResponse extends BaseResponse {

	/**
	 * 本次请求的唯一标识，由系统自动生成，用于追查结果有异常时使用
	 */
	@JsonProperty(value = "request_id")
	private String requestId;

	/**
	 * 逆地址解析结果
	 */
	private ResultDTO result;

	@NoArgsConstructor
	@Data
	public static class ResultDTO {

		/**
		 * 解析到的坐标（GCJ02坐标系）
		 */
		private LocationDTO location;

		/**
		 * 以行政区划+道路+门牌号等信息组成的标准格式化地址
		 */
		private String address;

		/**
		 * 结合知名地点形成的描述性地址，更具人性化特点
		 */
		@JsonProperty("formatted_addresses")
		private FormattedAddressesDTO formattedAddresses;

		/**
		 * 地址部件，address不满足需求时可自行拼接
		 */
		@JsonProperty("address_component")
		private AddressComponentDTO addressComponent;

		/**
		 * 行政区划信息
		 */
		@JsonProperty("ad_info")
		private AdInfoDTO adInfo;

		/**
		 * 坐标相对位置参考
		 */
		@JsonProperty("address_reference")
		private AddressReferenceDTO addressReference;

		/**
		 * 查询的周边poi的总数，仅在传入参数get_poi=1时返回
		 */
		@JsonProperty("poi_count")
		private Integer poiCount;

		/**
		 * 周边地点（POI/AOI）列表，数组中每个子项为一个POI/AOI对象
		 * 说明：POI即地点，如一个便利店，往往因其面积较小，其位置一般仅会标为为一个点，而学校、小区等往往面积较大，通常会有一定的地理范围，即所谓AOI，如果所请求的经纬度在AOI内，其距离会为0，且方位描述为“内”，如果是一个面积较小的地点，或不在AOI内，距离会>0，方位描述会为具体方位词，如“东”
		 */
		private List<PoisDTO> pois;

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
		public static class FormattedAddressesDTO {

			/**
			 * 推荐使用的地址描述，描述精确性较高
			 */
			private String recommend;

			/**
			 * 粗略位置描述
			 */
			private String rough;

		}

		@Data
		public static class AddressComponentDTO {

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

		@Data
		public static class AdInfoDTO {

			/**
			 * 国家代码（ISO3166标准3位数字码）
			 */
			@JsonProperty("nation_code")
			private String nationCode;

			/**
			 * 行政区划代码，详见：<a href=
			 * "https://lbs.qq.com/service/webService/webServiceGuide/webServiceDistrict#6">行政区划代码说明</a>
			 */
			private Integer adcode;

			/**
			 * 城市代码，由国家码+行政区划代码（提出城市级别）组合而来，总共为9位
			 */
			@JsonProperty("city_code")
			private String cityCode;

			/**
			 * 城市电话区号
			 */
			@JsonProperty("phone_area_code")
			private String phoneAreaCode;

			/**
			 * 行政区划名称
			 */
			private String name;

			/**
			 * 行政区划中心点坐标
			 */
			private ResultDTO.LocationDTO location;

			/**
			 * 国家
			 */
			private String nation;

			/**
			 * 省 / 直辖市
			 */
			private String province;

			/**
			 * 市，如果当前城市为省直辖县级区划，此字段会返回为空，由district字段返回。
			 * <p>
			 * 注：省直辖县级区划adcode第3和第4位分别为9、0，如济源市adcode为419001
			 */
			private String city;

			/**
			 * 区
			 */
			private String district;

		}

		@Data
		public static class AddressReferenceDTO {

			/**
			 * 知名区域，如商圈或人们普遍认为有较高知名度的区域
			 */
			@JsonProperty("famous_area")
			private FamousAreaDTO famousArea;

			/**
			 * 商圈，目前与famous_area一致
			 */
			@JsonProperty("business_area")
			private FamousAreaDTO businessArea;

			/**
			 * 乡镇街道
			 */
			private TownDTO town;

			/**
			 * 一级地标，可识别性较强、规模较大的地点、小区等 【注】：对象结构同 famous_area
			 */
			@JsonProperty("landmark_l1")
			private FamousAreaDTO landmarkL1;

			/**
			 * 二级地标，较一级地标更为精确，规模更小 【注】：对象结构同 famous_area
			 */
			@JsonProperty("landmark_l2")
			private FamousAreaDTO landmarkL2;

			/**
			 * 街道 【注】：对象结构同 famous_area
			 */
			private FamousAreaDTO street;

			/**
			 * 门牌 【注】：对象结构同 famous_area
			 */
			@JsonProperty("street_number")
			private FamousAreaDTO streetNumber;

			/**
			 * 交叉路口 【注】：对象结构同 famous_area
			 */
			private FamousAreaDTO crossroad;

			/**
			 * 水系 【注】：对象结构同 famous_area
			 */
			private FamousAreaDTO water;

			/**
			 * 海洋信息
			 */
			private OceanDTO ocean;

			@Data
			public static class FamousAreaDTO {

				/**
				 * 地点唯一标识
				 */
				private String id;

				/**
				 * 名称/标题
				 */
				private String title;

				/**
				 * 坐标
				 */
				private ResultDTO.LocationDTO location;

				/**
				 * 此参考位置到输入坐标的直线距离
				 */
				@JsonProperty("_distance")
				private Integer distance;

				/**
				 * 此参考位置到输入坐标的方位关系，如：北、南、内
				 */
				@JsonProperty("_dir_desc")
				private String dirDesc;

			}

			@Data
			public static class TownDTO {

				/**
				 * 地点唯一标识
				 */
				private String id;

				/**
				 * 名称/标题
				 */
				private String title;

				/**
				 * 坐标
				 */
				private ResultDTO.LocationDTO location;

				/**
				 * 此参考位置到输入坐标的直线距离
				 */
				@JsonProperty("_distance")
				private Integer distance;

				/**
				 * 此参考位置到输入坐标的方位关系，如：北、南、内
				 */
				@JsonProperty("_dir_desc")
				private String dirDesc;

			}

			@Data
			public static class OceanDTO {

				/**
				 * 地点唯一标识
				 */
				private String id;

				/**
				 * 名称/标题
				 */
				private String title;

			}

		}

		@Data
		public static class PoisDTO {

			/**
			 * 地点（POI）唯一标识
			 */
			private String id;

			/**
			 * 名称
			 */
			private String title;

			/**
			 * 地址
			 */
			private String address;

			/**
			 * 地点分类信息
			 */
			private String category;

			/**
			 * 坐标
			 */
			private ResultDTO.LocationDTO location;

			/**
			 * 行政区划信息
			 */
			@JsonProperty("ad_info")
			private AdInfoDTO adInfo;

			/**
			 * 该POI/AOI到逆地址解析传入的坐标的直线距离
			 */
			@JsonProperty("_distance")
			private Integer distance;

			/**
			 * 该POI/AOI在逆地址解析传入的坐标的相对方位描述，包括：东、东南、南、西南、西、西北、北、东北、内（输入经纬度在AOI范围内）
			 */
			@JsonProperty("_dir_desc")
			private String dirDesc;

			@Data
			public static class AdInfoDTO {

				/**
				 * 行政区划代码，详见：<a href=
				 * "https://lbs.qq.com/service/webService/webServiceGuide/webServiceDistrict#6">行政区划代码说明</a>
				 */
				private Integer adcode;

				/**
				 * 省
				 */
				private String province;

				/**
				 * 市，如果当前城市为省直辖县级区划，此字段会返回为空，由district字段返回。
				 * <p>
				 * 注：省直辖县级区划adcode第3和第4位分别为9、0，如济源市adcode为419001
				 */
				private String city;

				/**
				 * 区
				 */
				private String district;

			}

		}

	}

}
