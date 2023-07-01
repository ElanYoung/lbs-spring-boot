package com.starimmortal.lbs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 筛选条件请求类
 *
 * @author william@StarImmortal
 * @date 2023/06/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterRequest {

	/**
	 * 筛选关键字
	 */
	private String key;

	/**
	 * 条件
	 */
	private String condition;

	/**
	 * 筛选值（多个值,分割）
	 */
	private String value;

	@Override
	public String toString() {
		return this.key + this.condition + this.value;
	}

}
