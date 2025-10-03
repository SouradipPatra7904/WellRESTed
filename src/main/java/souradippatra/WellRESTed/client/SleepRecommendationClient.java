package souradippatra.WellRESTed.client;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;


@Component
public class SleepRecommendationClient {


    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:9595/api/recommendations"; // example local/mock service
    public static record SleepRecommendationDto(String recommendation, String advice) {}

    public SleepRecommendationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "recommendations", key = "#sleepQuality")
    public SleepRecommendationDto getRecommendation(int sleepQuality) {
        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
            .queryParam("quality", sleepQuality)
            .build().toUri();


        // Map response to DTO
        return restTemplate.getForObject(uri, SleepRecommendationDto.class);
    }
}