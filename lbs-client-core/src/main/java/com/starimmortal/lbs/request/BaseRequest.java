package com.starimmortal.lbs.request;

import com.starimmortal.lbs.enumeration.OutputTypeEnum;
import com.starimmortal.lbs.response.BaseResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpMethod;

/**
 * 请求基类
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@Data
@SuperBuilder
public abstract class BaseRequest<T extends BaseResponse> {

	/**
	 * 开发者密钥
	 */
	private String key;

	/**
	 * 返回格式
	 */
	private String output;

	/**
	 * 请求URL
	 * @return 请求URL
	 */
	public abstract String getUrl();

	/**
	 * 请求方法
	 * @return 请求方法
	 */
	public abstract HttpMethod getHttpMethod();

	/**
	 * 响应内容
	 * @return 响应内容
	 */
	public abstract Class<T> getResponseType();

	public BaseRequest() {
		this.output = OutputTypeEnum.JSON.name();
	}

}
