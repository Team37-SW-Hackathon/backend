package SWHackathonTeam37.SimplePrompt.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int subject; // 0 영어, 1 국어

    private String examFileName;

    private String originalFileUrl;

    private String examFileUrl;

    private String answerFileUrl;

    @CreatedDate
    private LocalDate createDate;

    private Exam(int subject, String originalFileUrl, String examFileUrl, String answerFileUrl) {
        this.subject = subject;
        this. originalFileUrl = originalFileUrl;
        this.examFileUrl = examFileUrl;
        this.answerFileUrl = answerFileUrl;
        this.examFileName = null;
    }

    public String getExamFileName() {
        if (examFileName == null) {
            return "시험지" + id;
        }
        return examFileName;
    }
}
