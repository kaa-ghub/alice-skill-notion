package kaaghub.alice.skill.notion.dto;


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
public class Request {

    @JsonProperty("command")
    private String command = "";
    @JsonProperty("original_utterance")
    private String originalUtterance;
    @JsonProperty("type")
    private String type;
    @JsonProperty("markup")
    private Markup markup;
    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("nlu")
    private Nlu nlu;
}