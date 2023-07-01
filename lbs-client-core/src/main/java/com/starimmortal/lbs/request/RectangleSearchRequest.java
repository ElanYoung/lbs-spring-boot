package com.starimmortal.lbs.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.starimmortal.lbs.serializer.LbsRequestSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 矩形范围（屏幕视野内）搜索请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RectangleSearchRequest extends PlaceSearchRequest {

	/**
	 * 格式：boundary=rectangle(lat,lng,lat,lng) 子参数：由矩形左下角（西南）和右上角（东北）两对经纬度组成，格式顺序为纬度在前，经度在后
	 */
	@JsonSerialize(using = LbsRequestSerializer.class)
	private RectangleBoundaryRequest boundary;

}
