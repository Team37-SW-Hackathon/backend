package SWHackathonTeam37.SimplePrompt.domain;

import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long>, ExamQueryRepository {
    Slice<SimpleExam> findExamBySubject(int subject);
}
