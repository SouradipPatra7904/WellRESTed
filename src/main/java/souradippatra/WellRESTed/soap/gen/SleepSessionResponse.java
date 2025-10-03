package souradippatra.WellRESTed.soap.gen;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SleepSessionResponse", namespace = "http://souradippatra.com/wellrested/soap")
public class SleepSessionResponse {
    private Long id;
    private Long userId;
    private String startTime;
    private String endTime;
    private Integer sleepQuality;
    private String notes;
    private String recommendation;
    private String advice;

    // Add @XmlElement getters/setters for all fields
    @XmlElement public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @XmlElement public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    @XmlElement public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    @XmlElement public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    @XmlElement public Integer getSleepQuality() { return sleepQuality; }
    public void setSleepQuality(Integer sleepQuality) { this.sleepQuality = sleepQuality; }

    @XmlElement public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @XmlElement public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }

    @XmlElement public String getAdvice() { return advice; }
    public void setAdvice(String advice) { this.advice = advice; }
}