package kaaghub.alice.skill.notion.service;

import kaaghub.alice.skill.notion.config.ApplicationProperties;
import kaaghub.alice.skill.notion.utils.Constants;
import lombok.RequiredArgsConstructor;
import notion.api.v1.NotionClient;
import notion.api.v1.model.common.PropertyType;
import notion.api.v1.model.common.RichTextType;
import notion.api.v1.model.pages.Page;
import notion.api.v1.model.pages.PageParent;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.model.search.SearchResult;
import notion.api.v1.model.search.SearchResults;
import notion.api.v1.request.pages.CreatePageRequest;
import notion.api.v1.request.search.SearchRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotionApiHelperImpl implements NotionApiHelper {
    private final ApplicationProperties applicationProperties;

    @Override
    @NotNull
    public String getHeaderText() {
        return "Note from " + LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    @Override
    @NotNull
    public String getOrCreatePage(NotionClient client, SearchRequest searchRequest) {
        SearchResults searchResults = client.search(searchRequest);
        Page page = null;
        if (searchResults.getResults().isEmpty()) {
            throw new RuntimeException(Constants.RIGHTS_FOR_SKILL_USER_EXCEPTION_MESSAGE);
        }
        Optional<SearchResult> result = checkTitleEquals(searchResults);
        if (result.isEmpty()) {
            PageProperty property = new PageProperty(UUID.randomUUID().toString(), PropertyType.Title,
                    List.of(createRichText(applicationProperties.getPage())));
            Map<String, PageProperty> propertiesMap = Map.of(Constants.NOTION_TITLE, property);
            CreatePageRequest createPageRequest = new CreatePageRequest(PageParent.page(searchResults.getResults().get(0).getId()), propertiesMap);
            Page createdPage = client.createPage(createPageRequest);
            return createdPage.getId();
        }
        return result.get().getId();
    }

    private Optional<SearchResult> checkTitleEquals(SearchResults searchResults) {
        return searchResults.getResults().stream()
                .filter(sr -> titleEquals(sr, applicationProperties.getPage())).findAny();
    }

    private boolean titleEquals(SearchResult sr, String title) {
        if (sr.asPage().getProperties().get("title") != null &&
                sr.asPage().getProperties().get("title").getTitle() != null) {
            return sr.asPage().getProperties().get("title")
                    .getTitle().stream().anyMatch(t -> getContentOfText(t).equals(title));
        }
        return false;
    }

    private String getContentOfText(PageProperty.RichText richText) {
        if (richText.getText() != null && richText.getText().getContent() != null) {
            return richText.getText().getContent();
        }
        return "null";
    }

    @Override
    @NotNull
    public PageProperty.RichText createRichText(String content) {
        return new PageProperty.RichText(RichTextType.Text, new PageProperty.RichText.Text(content));
    }

}
