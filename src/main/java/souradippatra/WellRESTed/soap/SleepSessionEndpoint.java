package souradippatra.WellRESTed.soap;

import souradippatra.WellRESTed.client.SleepRecommendationClient;
import souradippatra.WellRESTed.dto.SleepSessionDto;
import souradippatra.WellRESTed.service.SleepSessionService;
import souradippatra.WellRESTed.soap.gen.SleepSessionRequest;
import souradippatra.WellRESTed.soap.gen.SleepSessionResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SleepSessionEndpoint {

    private static final String NAMESPACE_URI = "http://souradippatra.com/wellrested/soap";

    private final SleepSessionService sleepSessionService;
    private final SleepRecommendationClient recommendationClient;

    public SleepSessionEndpoint(SleepSessionService sleepSessionService, SleepRecommendationClient recommendationClient) {
        this.sleepSessionService = sleepSessionService;
        this.recommendationClient = recommendationClient;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SleepSessionRequest")
    @ResponsePayload
    public SleepSessionResponse getSleepSessionRecommendation(@RequestPayload SleepSessionRequest request) {
        Long sessionId = request.getSessionId();

        SleepSessionDto dto = sleepSessionService.getById(sessionId);

        // Fetch recommendation dynamically
        if (dto.getSleepQuality() != null) {
            SleepRecommendationClient.SleepRecommendationDto rec = recommendationClient.getRecommendation(dto.getSleepQuality());
            dto.setRecommendation(rec.recommendation());
            dto.setAdvice(rec.advice());
        }

        // Map DTO to SOAP Response
        SleepSessionResponse response = new SleepSessionResponse();
        response.setId(dto.getId());
        response.setUserId(dto.getUserId());
        response.setStartTime(dto.getStartTime().toString());
        response.setEndTime(dto.getEndTime().toString());
        response.setSleepQuality(dto.getSleepQuality());
        response.setNotes(dto.getNotes());
        response.setRecommendation(dto.getRecommendation());
        response.setAdvice(dto.getAdvice());

        return response;
    }
}
