package com.starimmortal.lbs.autoconfigure;

import com.starimmortal.lbs.LbsClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 腾讯服务位置配置类
 *
 * @author william@StarImmortal
 * @date 2023/06/13
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LbsProperties.class)
public class LbsAutoConfiguration {

	private final LbsProperties lbsProperties;

	public LbsAutoConfiguration(LbsProperties lbsProperties) {
		this.lbsProperties = lbsProperties;
	}

	@Bean
	public LbsClient lbsClient() {
		return new LbsClient(lbsProperties.getKey(), lbsProperties.getSk());
	}

}
