package SWHackathonTeam37.SimplePrompt.service;

import SWHackathonTeam37.SimplePrompt.config.GptConfig;
import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptQuestionRequest;
import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptRequest;
import SWHackathonTeam37.SimplePrompt.service.dto.response.GptResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GptService {

    private static final RestTemplate restTemplate = new RestTemplate();
    public HttpEntity<GptRequest> buildHttpEntity(GptRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(GptConfig.MEDIA_TYPE));
        headers.add(GptConfig.AUTHORIZATION, GptConfig.BEARER + GptConfig.API_KEY);
        return new HttpEntity<>(request, headers);
    }

    public GptResponse getResponse(HttpEntity<GptRequest> requestedHttpEntity) {
        ResponseEntity<GptResponse> responseEntity = restTemplate.postForEntity(
                GptConfig.URL,
                requestedHttpEntity,
                GptResponse.class);

        return responseEntity.getBody();
    }

    public GptResponse askRequest(GptQuestionRequest request) {
        GptRequest gptRequest = new GptRequest(
                GptConfig.MODEL,
                request.question(),
                GptConfig.MAX_TOKEN,
                GptConfig.TEMPERATURE,
                GptConfig.TOP_P
        );
        HttpEntity<GptRequest> buildHttpEntity = this.buildHttpEntity(gptRequest);
        return this.getResponse(buildHttpEntity);
    }

}
