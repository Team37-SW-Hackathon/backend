package SWHackathonTeam37.SimplePrompt.service;

import SWHackathonTeam37.SimplePrompt.domain.ExamRepository;
import SWHackathonTeam37.SimplePrompt.service.dto.response.ExamAssembler;
import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void createDocument(List<String> passageList, List<String> questionList) {
        XWPFDocument document = new XWPFDocument();

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        String result = "";
        for (int i = 0; i < passageList.size(); i++) {
            result += Integer.toString(i+1) + passageList.get(i) + "\n" + questionList.get(i);
        }

        run.setText(result);

       String fileName = "시험지" + Math.random() * 10000 +".docx";
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            document.write(outputStream);
            System.out.println("Word 파일이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ExamAssembler getList(int subject) {
        Slice<SimpleExam> result = examRepository.findExamBySubject(subject);
        return new ExamAssembler(result.getContent(), result.hasNext());
    }

    public void deleteList(List<Long> idList) {
        for (Long id : idList) {
            examRepository.deleteById(id);
        }
    }
}
