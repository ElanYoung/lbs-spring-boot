package com.starimmortal.lbs;

import com.starimmortal.lbs.enumeration.LocationTypeEnum;
import com.starimmortal.lbs.enumeration.SearchPolicyEnum;
import com.starimmortal.lbs.request.*;
import com.starimmortal.lbs.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = { LbsClient.class })
@Slf4j
public class LbsClientTest {

	private LbsClient lbsClient;

	@BeforeEach
	public void setUp() {
		lbsClient = new LbsClient("", "");
	}

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

	@Test
	public void placeDetail() {
		PlaceSearchResponse response = lbsClient.placeDetail("6621879543162709731");
		log.info(response.toString());
	}

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

	@Test
	public void reverseGeocoder() {
		LocationRequest locationRequest = LocationRequest.builder().lat(39.984154).lng(116.307490).build();
		ReverseGeocoderRequest reverseGeocoderRequest = ReverseGeocoderRequest.builder()
			.location(locationRequest)
			.getSubpois(1)
			.poiOptions("address_format=short;radius=5000;policy=2")
			.build();
		ReverseGeocoderResponse response = lbsClient.reverseGeocoder(reverseGeocoderRequest);
		log.info(response.toString());
	}

	@Test
	public void geocoder() {
		GeocoderResponse response = lbsClient.geocoder("北京市海淀区彩和坊路海淀西大街74号");
		log.info(response.toString());
	}

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

	@Test
	public void ipLocation() {
		IpLocationResponse response = lbsClient.ipLocation("221.224.9.195");
		log.info(response.toString());
	}

}