package kaaghub.alice.skill.notion.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Card {
    private String type;
    private String image_id;
    private String title;
    private String description;
    private Button button;
}
