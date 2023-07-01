package com.starimmortal.lbs.constant;

/**
 * 腾讯位置服务请求URL常量
 *
 * @author william@StarImmortal
 * @date 2023/06/14
 */
public class ApiUrlConstant {

	/**
	 * 腾讯位置服务基础域名地址
	 */
	public final static String LBS_BASE_URL = "https://apis.map.qq.com";

	/**
	 * 地点搜索（包括周边搜索、城市/区域搜索、矩形范围（屏幕视野内）搜索）
	 */
	public final static String PLACE_SEARCH_URL = "/ws/place/v1/search";

	/**
	 * 周边推荐（explore）
	 */
	public final static String PLACE_EXPLORE_URL = "/ws/place/v1/explore";

	/**
	 * ID查询（detail）
	 */
	public final static String PLACE_DETAIL_URL = "/ws/place/v1/detail";

	/**
	 * 关键词输入提示
	 */
	public final static String PLACE_SUGGESTION_URL = "/ws/place/v1/suggestion";

	/**
	 * 逆地址解析（坐标位置描述）与地址解析（地址转坐标）
	 */
	public final static String GEOCODER_URL = "/ws/geocoder/v1";

	/**
	 * IP定位
	 */
	public final static String LOCATION_IP_URL = "/ws/location/v1/ip";

}
