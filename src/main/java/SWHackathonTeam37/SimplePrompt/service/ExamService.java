package SWHackathonTeam37.SimplePrompt.service;

import SWHackathonTeam37.SimplePrompt.domain.ExamRepository;
import SWHackathonTeam37.SimplePrompt.service.dto.response.ExamAssembler;
import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
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

    public void workbook1(String doc){

        List<String> workbook = new ArrayList<>();

        workbook = doc.split(" ");

        Collections.shuffle(workbook);

        return workbook;
    }

    public void workbook2(String doc){

        List<String> workbook = new ArrayList<>();

        workbook = doc.split(".");

        return workbook;

    }

    public void workbook3(String doc){

        List<String> var = new ArrayList<>();

        List<String> workbook = new ArrayList<>();
        
        int len = 0;

        var = doc.split(".");

        len = var.length;

        if (len>1){
            for(int i = 1 ; i < len ; i += 2){
                workbook.add(var[i-1] + var[i]);
            }
        
            if(len & 2 == 1){
                workbook.add(var[-1]);
            }
        }
        
        Collections.shuffle(workbook);

        return workbook;
    }

    private void uploadToAmazonS3() {
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
