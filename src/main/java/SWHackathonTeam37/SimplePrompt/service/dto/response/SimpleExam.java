package SWHackathonTeam37.SimplePrompt.service.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SimpleExam {
    private final long id;
    private final int subject;
    private final String examFileName;
    private final LocalDate createDate;
    private final String examFileUrl;
    private final String answerFileUrl;

    @QueryProjection
    public SimpleExam(long id, int subject, String examFileName, LocalDate createDate, String examFileUrl, String answerFileUrl) {
        this.id = id;
        this.subject = subject;
        this.examFileName = examFileName;
        this.createDate = createDate;
        this.examFileUrl = examFileUrl;
        this.answerFileUrl = answerFileUrl;
    }
}
