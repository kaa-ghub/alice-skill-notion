package kaaghub.alice.skill.notion.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString(callSuper = true)
public class Response {

    @JsonProperty("text")
    private String text;
    @JsonProperty("tts")
    private String tts;
    @JsonProperty("card")
    private Card card;
    @JsonProperty("buttons")
    private List<Button> buttons = null;
    @JsonProperty("end_session")
    private Boolean endSession;

    public Response(String text) {
        this.text = text;
        this.tts = text;
    }
}
