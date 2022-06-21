package kaaghub.alice.skill.notion.service;

import kaaghub.alice.skill.notion.dto.AliceRequest;
import kaaghub.alice.skill.notion.dto.AliceResponse;
import kaaghub.alice.skill.notion.dto.Response;
import kaaghub.alice.skill.notion.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AliceService {
    private final NotionService notionService;

    public AliceResponse processRequest(AliceRequest request) {
        String command = request.getRequest().getCommand();
        AliceResponse aliceResponse = new AliceResponse();
        try {
            notionService.addNote(command);
        } catch (Exception exception) {
            final Response response = new Response(Constants.ERROR_ANSWER);
            aliceResponse.setResponse(response);
        } finally {
            final Response response = new Response(Constants.SUCCESS_ANSWER);
            aliceResponse.setResponse(response);
        }
        aliceResponse.setVersion(Constants.VERSION);
        return aliceResponse;
    }
}
