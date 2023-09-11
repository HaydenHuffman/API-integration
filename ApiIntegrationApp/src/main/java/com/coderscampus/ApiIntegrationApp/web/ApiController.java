package com.coderscampus.ApiIntegrationApp.web;

import java.net.URI;
import java.util.Optional;

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
	public ResponseEntity<WeekResponse> getWeekMeals(Optional<String> numCalories, Optional<String> diet, Optional<String> exclusions) {
		RestTemplate rt = new RestTemplate();	
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlannerUrl)
									  .queryParam("apiKey", "db301bc313c34110908f5971652555b9")
									  .queryParam("timeFrame", "week");
		
		
		numCalories.ifPresent(value -> builder.queryParam("targetCalories", value));
		diet.ifPresent(value -> builder.queryParam("diet", value));
		exclusions.ifPresent(value -> builder.queryParam("exclude", value));

		URI uri = builder.build().toUri();

		
		ResponseEntity<WeekResponse> response = rt.getForEntity(uri, WeekResponse.class);
		return response;
		
	}
	
	@GetMapping("mealplanner/day")
	
	public ResponseEntity<DayResponse> getDayMeals(Optional<String> numCalories, Optional<String> diet, Optional<String> exclusions) {
		RestTemplate rt = new RestTemplate();	
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + mealPlannerUrl)
								      .queryParam("apiKey", "db301bc313c34110908f5971652555b9")
									  .queryParam("timeFrame", "day");

										
						numCalories.ifPresent(value -> builder.queryParam("targetCalories", value));
						diet.ifPresent(value -> builder.queryParam("diet", value));
						exclusions.ifPresent(value -> builder.queryParam("exclude", value));

						URI uri = builder.build().toUri();

		ResponseEntity<DayResponse> response = rt.getForEntity(uri, DayResponse.class);
		return response;
		
	}

}
