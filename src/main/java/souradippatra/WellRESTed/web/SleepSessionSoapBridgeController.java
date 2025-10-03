package souradippatra.WellRESTed.web;

import souradippatra.WellRESTed.client.SleepSessionSoapClient;
import souradippatra.WellRESTed.soap.gen.SleepSessionResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sleep-sessions")
public class SleepSessionSoapBridgeController {

    private final SleepSessionSoapClient soapClient;

    public SleepSessionSoapBridgeController(SleepSessionSoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @GetMapping("/{id}/soap")
    public ResponseEntity<SleepSessionResponse> getSleepSessionViaSoap(@PathVariable Long id) {
        SleepSessionResponse response = soapClient.getSleepSessionById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}

