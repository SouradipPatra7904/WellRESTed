package souradippatra.WellRESTed.client;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import souradippatra.WellRESTed.soap.gen.SleepSessionRequest;
import souradippatra.WellRESTed.soap.gen.SleepSessionResponse;

@Component
public class SleepSessionSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public SleepSessionSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    // ✅ Simplified function signature: just takes an id
    public SleepSessionResponse getSleepSessionById(Long id) {
        // Build SOAP request internally
        SleepSessionRequest request = new SleepSessionRequest();
        request.setSessionId(id);

        return (SleepSessionResponse) webServiceTemplate
                .marshalSendAndReceive("http://localhost:9595/ws", request);
    }
}
