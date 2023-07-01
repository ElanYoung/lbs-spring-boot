package com.starimmortal.lbs.enumeration;

import lombok.Getter;

/**
 * 搜索策略枚举
 *
 * @author william@StarImmortal
 * @date 2023/06/15
 */
@Getter
public enum SearchPolicyEnum {

	/**
	 * 地点签到场景
	 */
	LOCATION_CHECK_IN_SCENE(1),

	/**
	 * 位置共享场景
	 */
	LOCATION_SHARING_SCENE(2);

	private final Integer policy;

	SearchPolicyEnum(Integer policy) {
		this.policy = policy;
	}

}
