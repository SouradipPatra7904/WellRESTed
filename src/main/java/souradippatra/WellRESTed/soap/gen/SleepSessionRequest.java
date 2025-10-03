package souradippatra.WellRESTed.soap.gen;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "SleepSessionRequest", namespace = "http://souradippatra.com/wellrested/soap")
public class SleepSessionRequest {
    private Long sessionId;

    @XmlElement
    public Long getSessionId() { return sessionId; }
    public void setSessionId(Long sessionId) { this.sessionId = sessionId; }
}

