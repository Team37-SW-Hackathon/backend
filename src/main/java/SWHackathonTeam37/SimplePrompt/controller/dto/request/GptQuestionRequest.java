package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
public record GptQuestionRequest (
        @NotBlank(message = "질문은 필수 입력입니다.")
        String question
) {}
