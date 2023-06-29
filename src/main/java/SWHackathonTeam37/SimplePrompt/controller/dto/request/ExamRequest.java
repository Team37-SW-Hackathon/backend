package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import javax.validation.constraints.NotBlank;

public record ExamRequest(
        @NotBlank(message = "파일 url은 필수입니다.")
        String fileUrl,

        @NotBlank(message = "과목은 필수입니다.")
        int subject,

        @NotBlank(message = "시험지 유형은 필수입니다.")
        int examType,

        @NotBlank(message = "문제 유형은 필수입니다.")
        int questionType
) {
}
