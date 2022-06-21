package kaaghub.alice.skill.notion.service;

import kaaghub.alice.skill.notion.config.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notion.api.v1.NotionClient;
import notion.api.v1.http.OkHttp3Client;
import notion.api.v1.model.blocks.Block;
import notion.api.v1.model.blocks.HeadingTwoBlock;
import notion.api.v1.model.blocks.ParagraphBlock;
import notion.api.v1.request.search.SearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotionServiceImpl implements NotionService {
    private final NotionApiHelper notionApiHelper;
    private final ApplicationProperties applicationProperties;
    @Override
    public void addNote(String text) {
        try (NotionClient client = new NotionClient(applicationProperties.getSecret())) {
            client.setHttpClient(new OkHttp3Client());
            SearchRequest searchRequest = new SearchRequest("", new SearchRequest.SearchFilter("page", "object"));
            String pageId = notionApiHelper.getOrCreatePage(client, searchRequest);

            String headerText = notionApiHelper.getHeaderText();
            ParagraphBlock.Element textElement =
                    new ParagraphBlock.Element(List.of(notionApiHelper.createRichText(text)));
            HeadingTwoBlock.Element headerElement =
                    new HeadingTwoBlock.Element(
                            List.of(notionApiHelper.createRichText(headerText)), null, null);

            Block header = new HeadingTwoBlock(headerElement);
            Block block = new ParagraphBlock(textElement);
            client.appendBlockChildren(pageId, List.of(header, block));
        }
    }


}
