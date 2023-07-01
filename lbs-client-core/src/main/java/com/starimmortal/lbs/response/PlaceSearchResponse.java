package com.starimmortal.lbs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 地点搜索响应结果类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlaceSearchResponse extends BaseResponse {

	/**
	 * 本次请求的唯一标识，由系统自动生成，用于追查结果有异常时使用
	 */
	@JsonProperty(value = "request_id")
	private String requestId;

	/**
	 * 本次搜索结果总数，另外本服务限制最多返回200条数据(data)，翻页（page_index）超过搜索结果总数返回空，未超过搜索总数但超过200条限制时，将返回最后一页数据。
	 */
	private Integer count;

	/**
	 * 搜索结果POI（地点）数组，每项为一个POI（地点）对象
	 */
	private List<DataDTO> data;

	/**
	 * 搜索公交线路数组，每项为一个公交路线对象
	 */
	private List<LinesDTO> lines;

	/**
	 * 子地点列表，仅在输入参数get_subpois=1时返回
	 */
	@JsonProperty("sub_pois")
	private List<SubPoisDTO> subPois;

	/**
	 * 统计结果数组
	 */
	private List<ClusterDTO> cluster;

	/**
	 * POI数据所属地区
	 */
	private DataDTO region;

	@Data
	public static class DataDTO {

		/**
		 * POI（地点）唯一标识
		 */
		private String id;

		/**
		 * POI（地点）名称
		 */
		private String title;

		/**
		 * 地址
		 */
		private String address;

		/**
		 * 电话
		 */
		private String tel;

		/**
		 * POI（地点）分类
		 */
		private String category;

		/**
		 * POI类型，值说明：0:普通POI / 1:公交车站 / 2:地铁站 / 3:公交线路 / 4:行政区划
		 */
		private Integer type;

		/**
		 * 坐标
		 */
		private LocationDTO location;

		/**
		 * 距离，单位： 米，在周边搜索、城市范围搜索传入定位点时返回
		 */
		@JsonProperty("_distance")
		private Double distance;

		/**
		 * 行政区划信息
		 */
		@JsonProperty("ad_info")
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

	@EqualsAndHashCode(callSuper = true)
	@Data
	public static class SubPoisDTO extends DataDTO {

		/**
		 * 主地点ID，对应data中的地点ID
		 */
		@JsonProperty("parent_id")
		private String parentId;

	}

	@Data
	public static class LinesDTO {

		/**
		 * 公交线路唯一标识
		 */
		private String id;

		/**
		 * 公交线路名称
		 */
		private String title;

		/**
		 * 当前公交线路的始发站
		 */
		private OriginDTO origin;

		/**
		 * 当前公交线路的终点站
		 */
		private DestinationDTO destination;

		@Data
		public static class OriginDTO {

			/**
			 * 始发站站点名称
			 */
			private String title;

		}

		@Data
		public static class DestinationDTO {

			/**
			 * 终点站站点名称
			 */
			private String title;

		}

	}

	@Data
	public static class ClusterDTO {

		/**
		 * 城市名称
		 */
		private String title;

		/**
		 * 根据搜索条件，在该城市搜到的结果数
		 */
		private Integer count;

	}

}
