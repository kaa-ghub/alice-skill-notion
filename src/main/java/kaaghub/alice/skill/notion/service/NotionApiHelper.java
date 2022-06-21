package kaaghub.alice.skill.notion.service;

import notion.api.v1.NotionClient;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.request.search.SearchRequest;
import org.jetbrains.annotations.NotNull;

public interface NotionApiHelper {
    @NotNull
    String getHeaderText();

    @NotNull
    String getOrCreatePage(NotionClient client, SearchRequest searchRequest);

    @NotNull
    PageProperty.RichText createRichText(String content);
}
