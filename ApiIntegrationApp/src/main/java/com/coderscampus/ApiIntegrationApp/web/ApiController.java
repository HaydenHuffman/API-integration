package com.coderscampus.ApiIntegrationApp.web;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.ApiIntegrationApp.dto.DayResponse;
import com.coderscampus.ApiIntegrationApp.dto.WeekResponse;

@RestController
public class ApiController {
	
	@Value("${spoonacular.urls.base}")
	private String baseUrl;
	
	@Value("${spoonacular.urls.mealplan}")
	private String mealPlannerUrl;
	
	@GetMapping("mealplanner/week")
	public ResponseEntity<WeekResponse> getWeekMeals(String numCalories, String diet, String exclusions) {
		RestTemplate rt = new RestTemplate();	
		
		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlannerUrl)
									  .queryParam("apiKey", "db301bc313c34110908f5971652555b9")
									  .queryParam("timeFrame", "week")
									  .queryParam("targetCalories", numCalories)
									  .queryParam("diet", diet)
									  .queryParam("exclude", exclusions)
									  .build()
									  .toUri();
		ResponseEntity<WeekResponse> response = rt.getForEntity(uri, WeekResponse.class);
		return response;
		
	}
	
	@GetMapping("mealplanner/day")
	
	public ResponseEntity<DayResponse> getDayMeals(String numCalories, String diet, String exclusions) {
		RestTemplate rt = new RestTemplate();	
		
		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlannerUrl)
								      .queryParam("apiKey", "db301bc313c34110908f5971652555b9")
									  .queryParam("timeFrame", "day")
									  .queryParam("targetCalories", numCalories)
									  .queryParam("diet", diet)
									  .queryParam("exclude", exclusions)
									  .build()
									  .toUri();
		ResponseEntity<DayResponse> response = rt.getForEntity(uri, DayResponse.class);
		return response;
		
	}

}
