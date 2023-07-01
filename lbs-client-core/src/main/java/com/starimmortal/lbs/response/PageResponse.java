package com.starimmortal.lbs.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页响应结果基类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResponse extends BaseResponse {

	/**
	 * 本次搜索结果总数，另外本服务限制最多返回200条数据(data)，
	 * 翻页（page_index）超过搜索结果总数返回空，未超过搜索总数但超过200条限制时，将返回最后一页数据。
	 */
	private Integer count;

}
