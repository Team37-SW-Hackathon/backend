package SWHackathonTeam37.SimplePrompt.service;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
import SWHackathonTeam37.SimplePrompt.config.GptConfig;
import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptMessage;
import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptQuestionRequest;
import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptRequest;
import SWHackathonTeam37.SimplePrompt.service.dto.response.GptResponse;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GptService {

    private static RestTemplate restTemplate;

    public HttpEntity<GptRequest> buildHttpEntity(GptRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + GptConfig.API_KEY);
        return new HttpEntity<>(request, headers);
    }

    public ResponseEntity<GptResponse> getResponse(HttpEntity<GptRequest> requestedHttpEntity) {
        restTemplate = new RestTemplate();
        ResponseEntity<GptResponse> responseEntity = restTemplate.postForEntity(
                GptConfig.URL,
                requestedHttpEntity,
                GptResponse.class);

        return responseEntity;
    }

    public GptResponse askRequest(GptQuestionRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        List<GptMessage> messages = new ArrayList<>();
        messages.add(GptMessage.builder()
                .role("user")
                .content(request.getQuestion())
                .build());

        GptRequest gptRequest = new GptRequest(
                GptConfig.MODEL,
                GptConfig.MAX_TOKEN,
                GptConfig.TEMPERATURE,
                messages,
                GptConfig.TOP_P,
                false
        );

        HttpEntity<GptRequest> builtHttpEntity = buildHttpEntity(gptRequest);
        ResponseEntity<GptResponse> responseEntity = getResponse(builtHttpEntity);
        return responseEntity.getBody();
    }
    public String getAnswer(GptQuestionRequest request) {
        GptResponse response = askRequest(request);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
