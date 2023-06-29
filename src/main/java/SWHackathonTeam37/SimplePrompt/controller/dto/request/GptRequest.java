package SWHackathonTeam37.SimplePrompt.controller.dto.request;

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
        
        @NotBlank(message = "최대 토큰 수는 필수 입력입니다.")
        int maxTokens,

        @NotBlank(message = "temperature는 필수 입력입니다.")
        double temperature,

        @NotBlank(message = "top_p는 필수 입력입니다.")
        double topP
) {}

