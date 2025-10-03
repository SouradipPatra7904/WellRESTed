package souradippatra.WellRESTed.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import souradippatra.WellRESTed.client.SleepRecommendationClient;


@RestController
public class MockSleepRecommendationController {


@GetMapping("/api/recommendations")
public SleepRecommendationClient.SleepRecommendationDto getRecommendation(@RequestParam int quality) {
String recommendation = quality >= 8 ? "Excellent sleep!" : "Sleep more deeply";
String advice = quality >= 8 ? "Keep your routine" : "Try meditation before bedtime";
return new SleepRecommendationClient.SleepRecommendationDto(recommendation, advice);
}
}
