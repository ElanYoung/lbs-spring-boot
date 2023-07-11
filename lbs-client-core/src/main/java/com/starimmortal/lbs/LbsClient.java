package com.starimmortal.lbs;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.starimmortal.lbs.constant.ApiUrlConstant;
import com.starimmortal.lbs.exception.LbsClientException;
import com.starimmortal.lbs.handler.RestGetUriTemplateHandler;
import com.starimmortal.lbs.request.*;
import com.starimmortal.lbs.response.*;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 腾讯位置服务客户端
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
@NoArgsConstructor
public class LbsClient {

	/**
	 * 密钥
	 */
	private String key;

	/**
	 * 签名校验
	 */
	private String sign;

	public LbsClient(String key) {
		this.key = key;
	}

	public LbsClient(String key, String sign) {
		this.key = key;
		this.sign = sign;
	}

	public <T extends BaseResponse> T execute(BaseRequest<T> request) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new RestGetUriTemplateHandler());
		// 构造请求头
		HttpHeaders headers = new HttpHeaders();
		// 构造请求参数
		Map<String, Object> variables = buildRequestParams(request);
		// 构造请求体
		HttpEntity<Object> httpEntity = new HttpEntity<>(
				request.getHttpMethod().matches(HttpMethod.GET.name()) ? null : variables, headers);
		// 发送请求
		ResponseEntity<T> responseEntity = restTemplate.exchange(ApiUrlConstant.LBS_BASE_URL + request.getUrl(),
				request.getHttpMethod(), httpEntity, request.getResponseType(), variables);
		if (!responseEntity.getStatusCode().is2xxSuccessful()) {
			throw new LbsClientException();
		}
		T body = responseEntity.getBody();
		if (!Objects.requireNonNull(body).getStatus().equals(0)) {
			throw new LbsClientException(body.getMessage());
		}
		return responseEntity.getBody();
	}

	/**
	 * 周边搜索
	 * @param request 周边搜索请求参数
	 * @return 周边搜索响应结果
	 */
	public PlaceSearchResponse nearbyPlaceSearch(NearbySearchRequest request) {
		return execute(request);
	}

	/**
	 * 矩形范围（屏幕视野内）搜索
	 * @param request 矩形范围（屏幕视野内）搜索请求参数
	 * @return 矩形范围（屏幕视野内）搜索响应结果
	 */
	public PlaceSearchResponse rectanglePlaceSearch(RectangleSearchRequest request) {
		return execute(request);
	}

	/**
	 * 城市/区域搜索
	 * @param request 城市/区域搜索请求参数
	 * @return 城市/区域搜索搜索响应结果
	 */
	public PlaceSearchResponse regionPlaceSearch(RegionSearchRequest request) {
		return execute(request);
	}

	/**
	 * 周边推荐
	 * @param request 周边推荐请求参数
	 * @return 周边推荐响应结果
	 */
	public PlaceSearchResponse placeExplore(PlaceExploreRequest request) {
		return execute(request);
	}

	/**
	 * 地点搜索ID查询
	 * @param id 地点搜索ID查询请求参数
	 * @return 地点搜索ID查询响应结果
	 */
	public PlaceSearchResponse placeDetail(String id) {
		PlaceDetailRequest request = PlaceDetailRequest.builder().id(id).build();
		return execute(request);
	}

	/**
	 * 关键词输入提示
	 * @param request 关键词输入提示请求参数
	 * @return 关键词输入提示响应结果
	 */
	public PlaceSearchResponse placeSuggestion(PlaceSuggestionRequest request) {
		return execute(request);
	}

	/**
	 * 逆地址解析（坐标位置描述）
	 * @param request 逆地址解析请求参数
	 * @return 逆地址解析响应结果
	 */
	public ReverseGeocoderResponse reverseGeocoder(ReverseGeocoderRequest request) {
		return execute(request);
	}

	/**
	 * 地址解析（地址转坐标）
	 * @param address 地址
	 * @return 地址解析响应结果
	 */
	public GeocoderResponse geocoder(String address) {
		GeocoderRequest request = GeocoderRequest.builder().address(address).build();
		return execute(request);
	}

	/**
	 * 坐标转换
	 * @param request 坐标转换请求参数
	 * @return 坐标转换响应结果
	 */
	public CoordTranslateResponse coordTranslate(CoordTranslateRequest request) {
		return execute(request);
	}

	/**
	 * IP定位
	 * @param ip IP地址，缺省时会使用请求端的IP
	 * @return IP定位响应结果
	 */
	public IpLocationResponse ipLocation(String ip) {
		IpLocationRequest request = IpLocationRequest.builder().ip(ip).build();
		return execute(request);
	}

	/**
	 * 构建请求参数
	 * @param request 请求参数
	 * @return 完整请求URL
	 */
	@SneakyThrows
	private <T extends BaseResponse> Map<String, Object> buildRequestParams(BaseRequest<T> request) {
		request.setKey(this.key);
		Map<String, Object> params = new TreeMap<>();
		Class<?> clazz = request.getClass();
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				if (!params.containsKey(field.getName())) {
					if (Objects.nonNull(field.get(request))) {
						params.put(StrUtil.toUnderlineCase(field.getName()), field.get(request));
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		if (StringUtils.hasText(this.sign)) {
			params.put("sig", sign(request.getUrl(), params));
		}
		return params;
	}

	/**
	 * 签名计算
	 * <p>
	 * 1. 首先对参数进行排序：按参数名升序
	 * <p>
	 * 2. 请求路径+”?”+请求参数+SK进行拼接，并计算拼接后字符串md5值（字符必须为小写），即为签名(sig)
	 * @param params 请求参数
	 * @return sig
	 */
	private String sign(String url, Map<String, Object> params) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(url).append("?");
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (Objects.nonNull(entry.getValue())) {
				stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
			}
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(this.sign);
		return SecureUtil.md5(stringBuilder.toString());
	}

}
