package com.starimmortal.lbs.response;

import lombok.Data;

/**
 * 响应结果基类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@Data
public abstract class BaseResponse {

	/**
	 * 状态码
	 */
	private Integer status;

	/**
	 * 状态说明
	 */
	private String message;

}
