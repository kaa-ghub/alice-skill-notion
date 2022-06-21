package kaaghub.alice.skill.notion.dto;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Nlu {

    @JsonProperty("tokens")
    private List<String> tokens   = new ArrayList<>();
    @JsonProperty("entities")
    private List<Entity> entities = new ArrayList<>();
}