package SWHackathonTeam37.SimplePrompt.service.dto.response;

import java.util.List;

public record ExamAssembler (
        List<SimpleExam> list,
        boolean hasNext) {
}
