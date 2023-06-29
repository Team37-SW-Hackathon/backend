package SWHackathonTeam37.SimplePrompt.domain;

import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ExamQueryRepository {
    Slice<SimpleExam> findExamBySubject(int subject, Pageable pageable);
}
