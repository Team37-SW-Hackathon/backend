package SWHackathonTeam37.SimplePrompt.service.dto.response;

import java.util.List;

public record ExamResponse(
        List<ExamResponse> examList,
        boolean hasNext
) {
}
