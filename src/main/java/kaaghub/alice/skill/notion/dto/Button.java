package kaaghub.alice.skill.notion.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString(callSuper = true)
public class Button {

    @JsonProperty("title")
    private String              title;
    @JsonProperty("payload")
    private Payload             payload;
    @JsonProperty("url")
    private String              url;
    @JsonProperty("hide")
    private Boolean             hide;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}