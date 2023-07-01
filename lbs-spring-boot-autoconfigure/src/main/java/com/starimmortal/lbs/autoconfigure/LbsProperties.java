package com.starimmortal.lbs.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯服务位置配置属性
 *
 * @author william@StarImmortal
 * @date 2023/06/13
 */
@ConfigurationProperties(prefix = LbsProperties.LBS_PREFIX)
public class LbsProperties {

	/**
	 * 属性前缀
	 */
	public static final String LBS_PREFIX = "lbs";

	/**
	 * 密钥
	 */
	private String key;

	/**
	 * 签名校验
	 */
	private String sk;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

}
