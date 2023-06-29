package SWHackathonTeam37.SimplePrompt.domain;

import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static SWHackathonTeam37.SimplePrompt.domain.QExam.exam;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExamQueryRepositoryImpl implements ExamQueryRepository {
    private final JPAQueryFactory query;

    @Override
    public Slice<SimpleExam> findExamBySubject(int subject, Pageable pageable) {
        List<SimpleExam> result = query
                .select(Projections.constructor(SimpleExam.class, exam.id, exam.subject, exam.examFileName, exam.createDate, exam.examFileUrl, exam.answerFileUrl))
                .from(exam)
                .where(exam.subject.eq(subject))
                .orderBy(exam.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = query.selectFrom(exam)
                .where(exam.subject.eq(subject))
                .fetchCount();

        return new SliceImpl<>(result, pageable, validateHasNext(pageable, result.size(), totalCount));
    }

    private boolean validateHasNext(Pageable pageable, int contentSize, Long totalCount) {
        if (contentSize == pageable.getPageSize()) {
            return (long) contentSize * (pageable.getPageNumber() + 1) != totalCount;
        }
        return false;
    }
}
