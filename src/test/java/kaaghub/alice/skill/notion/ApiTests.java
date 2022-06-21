package kaaghub.alice.skill.notion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static kaaghub.alice.skill.notion.utils.Constants.SUCCESS_ANSWER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@AutoConfigureMockMvc
public class ApiTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200()
            throws Exception {

        String json = "{\n" +
                "\"meta\": {\n" +
                "\"locale\": \"ru-RU\",\n" +
                "\"timezone\": \"Europe/Moscow\",\n" +
                "\"client_id\": \"ru.yandex.searchplugin/5.80 (Samsung Galaxy; Android 4.4)\"\n" +
                "},\n" +
                "\"request\": {\n" +
                "\"type\": \"SimpleUtterance\",\n" +
                "\"markup\": {\n" +
                "\"dangerous_context\": true\n" +
                "},\n" +
                "\"command\": \"архангельск\",\n" +
                "\"original_utterance\": \"Алиса вызови игру в города. Архангельск.\",\n" +
                "\"payload\": {}\n" +
                "},\n" +
                "\"session\": {\n" +
                "\"new\": true,\n" +
                "\"session_id\": \"2eac4854-fce721f3-b845abba-20d60\",\n" +
                "\"message_id\": 4,\n" +
                "\"skill_id\": \"3ad36498-f5rd-4079-a14b-788652932056\",\n" +
                "\"user_id\": \"AC9WC3DF6FCE052E45A4566A48E6B7193774B84814CE49A922E163B8B29881DC\"\n" +
                "},\n" +
                "\"version\": \"1.0\"\n" +
                "}";

        mvc.perform(post("/talk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("response.text", is(SUCCESS_ANSWER)));
    }
}
