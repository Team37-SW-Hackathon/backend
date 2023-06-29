package SWHackathonTeam37.SimplePrompt.service;

import SWHackathonTeam37.SimplePrompt.domain.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;

    public void makeExam(String originalFileUrl, int subject, int exampType, int questionType) {
        // 1. pdf ->  word -> text 변환
        // 2. 지문 추출
        List<String> passageList = new ArrayList<>();
        // 3. GPT => 문제 만들어줘
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        // 4. 문제를 파일로
        // 5. 클라우드에 파일 올리기
    }
}
