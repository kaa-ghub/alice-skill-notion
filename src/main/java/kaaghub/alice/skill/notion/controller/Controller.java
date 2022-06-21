package kaaghub.alice.skill.notion.controller;

import kaaghub.alice.skill.notion.dto.AliceResponse;
import kaaghub.alice.skill.notion.service.AliceService;
import kaaghub.alice.skill.notion.dto.AliceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/v1")
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final AliceService aliceService;

    @PostMapping(value = "/talk", produces = MediaType.APPLICATION_JSON_VALUE)
    public AliceResponse talk(@RequestBody AliceRequest request)
    {

        AliceResponse aliceResponse = aliceService.processRequest(request);
        log.debug(request.toString());
        log.debug(request.getRequest().getCommand());
        return aliceResponse;
    }

}
