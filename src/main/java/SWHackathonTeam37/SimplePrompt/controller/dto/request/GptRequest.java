package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */

public record GptRequest (
        
        @NotBlank(message = "모델은 필수 입력입니다.")
        String model,
        
        @NotBlank(message = "텍스트는 필수 입력입니다.")
        String prompt,

        @JsonProperty("max_tokens")
        @NotBlank(message = "최대 토큰 수는 필수 입력입니다.")
        int maxTokens,

        @NotBlank(message = "temperature는 필수 입력입니다.")
        double temperature,

        @JsonProperty("top_p")
        @NotBlank(message = "top_p는 필수 입력입니다.")
        double topP
) {}

