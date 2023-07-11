<h1 align="center"><a href="https://lbs.qq.com/service/webService/webServiceGuide/webServiceOverview" target="_blank">📍 腾讯位置服务 WebService API Spring Boot Starter</a></h1>
<p align="center">
  <a href="https://doc.starimmortal.com"><img alt="author" src="https://img.shields.io/badge/author-ElanYoung-blue.svg"/></a>
  <a href="https://search.maven.org/search?q=g:com.starimmortal%20AND%20a:lbs-spring-boot-starter"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.starimmortal/lbs-spring-boot-starter?label=Maven%20Central"/></a>
  <a href="https://docs.spring.io/spring-boot/docs/2.7.12/reference/html/"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-2.7.12-brightgreen.svg"/></a>
  <a href="https://github.com/ElanYoung/lbs-spring-boot/blob/master/LICENSE"><img alt="LICENSE" src="https://img.shields.io/github/license/ElanYoung/lbs-spring-boot.svg"/></a>
</p>

<p align="center">
  <a href="https://github.com/ElanYoung/lbs-spring-boot/stargazers"><img alt="star" src="https://img.shields.io/github/stars/ElanYoung/lbs-spring-boot.svg?label=Stars&style=social"/></a>
  <a href="https://github.com/ElanYoung/lbs-spring-boot/network/members"><img alt="star" src="https://img.shields.io/github/forks/ElanYoung/lbs-spring-boot.svg?label=Fork&style=social"/></a>
  <a href="https://github.com/ElanYoung/lbs-spring-boot/watchers"><img alt="star" src="https://img.shields.io/github/watchers/ElanYoung/lbs-spring-boot.svg?label=Watch&style=social"/></a>
</p>

## 简介

腾讯地图 WebService API 是基于 HTTPS/HTTP 协议的数据接口。

开发者可以使用任何客户端、服务器和开发语言，按照腾讯地图 WebService API 规范，按需构建 HTTPS 请求，并获取结果数据（目前支持JSON/JSONP方式返回）。

[lbs-spring-boot-starter](https://github.com/ElanYoung/lbs-spring-boot) 是基于上述封装成 Java SDK 版本，可以帮助你更快地在 [Spring Boot](https://spring.io/projects/spring-boot) 之上构建 WebService API 应用。

[lbs-spring-boot-starter](https://github.com/ElanYoung/lbs-spring-boot) 目前集成了地点搜索、IP定位等接口。

## 快速开始

### 引入依赖

```xml
<dependency>
  <groupId>com.starimmortal</groupId>
  <artifactId>lbs-spring-boot-starter</artifactId>
  <version>1.0.1</version>
</dependency>
```

### 参数配置

```yaml
# 腾讯位置服务
lbs:
  # 开发者密钥
  key: OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77
  # 签名校验
  sg: ER1w0iqvajsow4a5tAC7OPfBVboHzMe
```

#### 申请开发者密钥

1）[注册](https://lbs.qq.com/dev/console/register?backurl=https%3A%2F%2Flbs.qq.com%2Fdev%2Fconsole%2Fhome)账号；如果您已经是腾讯位置服务开发者，请登录，并进入控制台。

2）[控制台-应用管理-我的应用](https://lbs.qq.com/dev/console/application/mine)中创建应用，并在相应的应用中创建Key。

#### 启用服务与安全设置

与授权IP方式比较，使用SN校验稍有开发量，但不必担心服务器换IP的问题。

选中SN校验后，会生成SecretKey (SK)，用于请求地图WebServiceAPI时计算签名，将签名做为参数（sig）附带到请求中，腾讯服务器会在收到请求后，使用相同的方式生成签名，并与请求中附带的签名进行比对，当一致时即为校验通过，反之则拒绝。

SecretKey (SK) 要注意保密，如有泄漏应尽快重新重成。

![签名校验](http://p.qpic.cn/lbsconsole/0/a35f28ecc450485fdfff77a2bdeeb05a/0)

## 基本使用

### [地点搜索](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch)

#### [周边搜索](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch#2)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    void nearbyPlaceSearch() {
        NearbyBoundaryRequest nearbyBoundaryRequest = NearbyBoundaryRequest.builder()
                .lat(28.681114)
                .lng(115.918377)
                .radius(1000)
                .autoExtend(1)
                .build();
        FilterRequest filterRequest = FilterRequest.builder().key("tel").condition("<>").value("null").build();
        NearbySearchRequest nearbySearchRequest = NearbySearchRequest.builder()
                .keyword("酒店")
                .boundary(nearbyBoundaryRequest)
                .getSubpois(1)
                .filter(filterRequest)
                .orderby("_distance")
                .build();
        PlaceSearchResponse response = lbsClient.nearbyPlaceSearch(nearbySearchRequest);
        log.info(response.toString());
    }
}
```

#### [城市/区域搜索](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch#3)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    void regionPlaceSearch() {
        RegionBoundaryRequest regionBoundaryRequest = RegionBoundaryRequest.builder()
                .cityName("北京")
                .lat(40.040493)
                .lng(116.273545)
                .autoExtend(1)
                .build();
        FilterRequest filterRequest = FilterRequest.builder().key("category").condition("=").value("公交站").build();
        RegionSearchRequest regionSearchRequest = RegionSearchRequest.builder()
                .keyword("酒店")
                .boundary(regionBoundaryRequest)
                .getSubpois(1)
                .filter(filterRequest)
                .build();
        PlaceSearchResponse response = lbsClient.regionPlaceSearch(regionSearchRequest);
        log.info(response.toString());
    }
}
```

#### [矩形范围（屏幕视野内）搜索](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch#4)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    void rectanglePlaceSearch() {
        RectangleBoundaryRequest rectangleBoundaryRequest = RectangleBoundaryRequest.builder()
                .southwestLat(39.907293)
                .southwestLng(116.368935)
                .northeastLat(39.914996)
                .northeastLng(116.379321)
                .build();
        FilterRequest filterRequest = FilterRequest.builder().key("category").condition("=").value("公交站").build();
        RectangleSearchRequest rectangleSearchRequest = RectangleSearchRequest.builder()
                .keyword("美食")
                .boundary(rectangleBoundaryRequest)
                .filter(filterRequest)
                .build();
        PlaceSearchResponse response = lbsClient.rectanglePlaceSearch(rectangleSearchRequest);
        log.info(response.toString());
    }
}
```

#### [周边推荐（explore）](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch#5)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    void placeExplore() {
        NearbyBoundaryRequest nearbyBoundaryRequest = NearbyBoundaryRequest.builder()
                .lat(40.040589)
                .lng(116.273543)
                .radius(1000)
                .autoExtend(1)
                .build();
        PlaceExploreRequest placeExploreRequest = PlaceExploreRequest.builder()
                .boundary(nearbyBoundaryRequest)
                .policy(SearchPolicyEnum.LOCATION_CHECK_IN_SCENE.getPolicy())
                .orderby("_distance")
                .addressFormat("short")
                .build();
        PlaceSearchResponse response = lbsClient.placeExplore(placeExploreRequest);
        log.info(response.toString());
    }
}
```

#### [ID查询（detail）](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSearch#6)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    public void placeDetail() {
        PlaceSearchResponse response = lbsClient.placeDetail("6621879543162709731");
        log.info(response.toString());
    }
}
```

### [关键词输入提示](https://lbs.qq.com/service/webService/webServiceGuide/webServiceSuggestion)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    public void placeSuggestion() {
        FilterRequest filterRequest = FilterRequest.builder().key("category").condition("=").value("公交站").build();
        PlaceSuggestionRequest placeSuggestionRequest = PlaceSuggestionRequest.builder()
                .keyword("美食")
                .region("北京")
                .policy(SearchPolicyEnum.LOCATION_CHECK_IN_SCENE.getPolicy())
                .filter(filterRequest)
                .addressFormat("short")
                .build();
        PlaceSearchResponse response = lbsClient.placeSuggestion(placeSuggestionRequest);
        log.info(response.toString());
    }
}
```

### [逆地址解析（坐标位置描述）](https://lbs.qq.com/service/webService/webServiceGuide/webServiceGcoder)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    public void reverseGeocoder() {
        LocationRequest locationRequest = LocationRequest.builder()
                .lat(39.984154)
                .lng(116.307490)
                .build();
        ReverseGeocoderRequest reverseGeocoderRequest = ReverseGeocoderRequest.builder()
                .location(locationRequest)
                .build();
        ReverseGeocoderResponse response = lbsClient.reverseGeocoder(reverseGeocoderRequest);
        log.info(response.toString());
    }
}
```

### [地址解析（地址转坐标）](https://lbs.qq.com/service/webService/webServiceGuide/webServiceGeocoder)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

    @Test
    public void geocoder() {
        GeocoderResponse response = lbsClient.geocoder("北京市海淀区彩和坊路海淀西大街74号");
        log.info(response.toString());
    }
}
```

**level（解析级别）取值表：**

| 值  | 解析级别               |
|:--:|:-------------------|
| 1  | 城市                 |
| 2  | 区、县                |
| 3  | 乡镇、街道              |
| 4  | 村、社区               |
| 5  | 开发区                |
| 6  | 热点区域、商圈            |
| 7  | 道路                 |
| 8  | 道路附属点：交叉口、收费站、出入口等 |
| 9  | 门址                 |
| 10 | 小区、大厦              |
| 11 | POI点               |

### [坐标转换](https://lbs.qq.com/service/webService/webServiceGuide/webServiceTranslate)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

	@Test
	public void coordTranslate() {
		List<LocationRequest> locations = new ArrayList<>();
		locations.add(new LocationRequest(39.12, 116.83));
		locations.add(new LocationRequest(30.21, 115.43));
		CoordTranslateRequest request = new CoordTranslateRequest();
		request.setLocations(locations);
		request.setType(LocationTypeEnum.BAIDU.getType());
		CoordTranslateResponse response = lbsClient.coordTranslate(request);
		log.info(response.toString());
	}
}
```

### [IP定位](https://lbs.qq.com/service/webService/webServiceGuide/webServiceIp)

```java
@SpringBootTest
@Slf4j
public class LbsClientTest {

    @Autowired
	private LbsClient lbsClient;

	@Test
	public void ipLocation() {
		IpLocationResponse response = lbsClient.ipLocation("221.224.9.195");
		log.info(response.toString());
	}
}
```

## TODO

- [x] 地点搜索
- [x] 关键词输入提示
- [x] 逆地址解析（坐标位置描述）
- [x] 地址解析（地址转坐标）
- [ ] 地址标准化/纠错/补全/验真（暂不支持）
- [ ] 路线规划（驾车/公交/步骑）
- [ ] 批量距离计算（矩阵）
- [ ] 货车路线规划 & 距离矩阵
- [ ] 行政区划
- [x] 坐标转换
- [x] IP定位
- [ ] 智能硬件定位（暂不支持）

## 参考资料

- [腾讯位置服务 WebService API](https://lbs.qq.com/service/webService/webServiceGuide/webServiceOverview)
- [腾讯地图POI分类表](https://lbs.qq.com/service/webService/webServiceGuide/webServiceAppendix)
- [状态码说明](https://lbs.qq.com/service/webService/webServiceGuide/status)

## 项目趋势

[![Stargazers over time](https://starchart.cc/ElanYoung/lbs-spring-boot.svg)](https://starchart.cc/ElanYoung/lbs-spring-boot)

## 开源协议

[Apache License](https://opensource.org/license/apache-2-0/)

Copyright (c) 2023 ElanYoung