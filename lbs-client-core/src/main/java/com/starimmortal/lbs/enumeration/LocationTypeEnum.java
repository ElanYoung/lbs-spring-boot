package com.starimmortal.lbs.enumeration;

import lombok.Getter;

/**
 * 坐标类型枚举
 *
 * @author william@StarImmortal
 * @date 2023/06/15
 */
@Getter
public enum LocationTypeEnum {

	/**
	 * GPS坐标
	 */
	GPS(1),

	/**
	 * sogou经纬度
	 */
	SOGOU(2),

	/**
	 * baidu经纬度
	 */
	BAIDU(3),

	/**
	 * mapbar经纬度
	 */
	MAPBAR(4),

	/**
	 * sogou墨卡托
	 */
	SOGOU_MOKATUO(6);

	private final Integer type;

	LocationTypeEnum(Integer type) {
		this.type = type;
	}

}
