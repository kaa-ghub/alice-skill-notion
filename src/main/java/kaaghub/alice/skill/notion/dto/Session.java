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
public class Session {

    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("new")
    private Boolean _new;
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("skill_id")
    private String skillId;
    @JsonProperty("user_id")
    private String userId;

}
