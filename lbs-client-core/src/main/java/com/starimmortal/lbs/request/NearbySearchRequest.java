package com.starimmortal.lbs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 周边搜索请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NearbySearchRequest extends PlaceSearchRequest {

	/**
	 * 格式：boundary=nearby(lat,lng,radius[, auto_extend])，子参数：
	 * 1.lat,lng：搜索中心点的经纬度，格式顺序为纬度在前，经度在后
	 * <p>
	 * 2.radius：搜索半径，单位：米，取值范围：10到1000
	 * <p>
	 * 3.auto_extend：[可选]当前范围无结果时，是否自动扩大范围，取值：0（不扩大）；1（默认）自动扩大范围（依次按照按1公里、2公里、5公里，最大到全城市范围搜索）
	 */
	private NearbyBoundaryRequest boundary;

	/**
	 * 是否返回子地点，如大厦停车场、出入口等取值：0：不返回（默认） 1：返回
	 */
	private Integer getSubpois;

	/**
	 * 排序，支持按距离由近到远排序，取值：_distance，说明： 1.周边搜索默认排序会综合考虑距离、权重等多方面因素
	 * <p>
	 * 2.设置按距离排序后则仅考虑距离远近，一些低权重的地点可能因距离近排在前面，导致体验下降
	 */
	private String orderby;

}
