package kaaghub.alice.skill.notion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AliceRequest {
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("request")
    private Request request;
    @JsonProperty("session")
    private Session session;
    @JsonProperty("version")
    private String version;
 }
