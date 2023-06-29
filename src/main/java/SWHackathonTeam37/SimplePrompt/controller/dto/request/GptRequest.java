package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */

@Getter
@NoArgsConstructor

public class GptRequest implements Serializable {
        private String model;
        @JsonProperty("max_tokens")
        private Integer maxTokens;
        private Double temperature;
        private List<GptMessage> messages;
        @JsonProperty("top_p")
        private Double topP;
        private Boolean stream;
        @Builder
        public GptRequest(String model, Integer maxTokens, Double temperature,
                          List<GptMessage> messages, Double topP, Boolean stream) {
                this.model = model;
                this.maxTokens = maxTokens;
                this.temperature = temperature;
                this.messages = messages;
                this.topP = topP;
                this.stream = stream;
        }
}

