package SWHackathonTeam37.SimplePrompt.service.dto.response;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
@Getter
@NoArgsConstructor

public class GptResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    @Getter
    @Setter
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;
    }

    @Getter
    @Setter
    public static class Choice {
        private GptMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
        private int index;
    }
}

