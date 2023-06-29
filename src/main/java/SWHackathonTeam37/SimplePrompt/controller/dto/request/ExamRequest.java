package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public record ExamRequest(
        @NotBlank(message = "파일 url은 필수입니다.")
        String fileUrl,

        @NotBlank(message = "과목은 필수입니다.")
        int subject,

        @NotBlank(message = "문제 유형은 필수입니다.")
        List<Integer> questionType,

        int startPage,

        int endPage
) {
}
