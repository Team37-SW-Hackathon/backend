package SWHackathonTeam37.SimplePrompt.service;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptQuestionRequest;
import SWHackathonTeam37.SimplePrompt.domain.ExamRepository;
import SWHackathonTeam37.SimplePrompt.service.dto.response.ExamAssembler;
import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final GptService gptService;
    public static final String first_prompt = "위 영어지문을 한 문장, 10단어 이내로 영어로 요약하세요\n.json 타입으로 key값은 summary를 사용하세요.";
    public static final String second_prompt = "위 요약문에서 핵심 단어 하나를 Json타입으로 작성하세요. key 값은 \"word\"를 사용하세요";
    public static final String third_prompt = "위 요약문에서 {word} 단어를 지우고 그 위치에 @@@기호를 넣어서 새 요약문을 Json타입으로 작성하세요. key 값은 \"script\"를 사용하세요.\n";
    public static final String fourth_prompt = "{word} 단어와 거리가 먼 단어 세 개를 작성하세요. key값은 \"1,2,3\"을 사용하세요.";

    public String first_question(String passage) {
        return gptService.getAnswer(new GptQuestionRequest(passage + "\n\n" + first_prompt));
    }

    public String second_question(String summary) {
        return gptService.getAnswer(new GptQuestionRequest(summary + "\n\n" + second_prompt));
    }

    public String third_question(String summary) {
        return gptService.getAnswer(new GptQuestionRequest(summary + "\n\n" + third_prompt));
    }
    public String fourth_question(String word) {
        return gptService.getAnswer(new GptQuestionRequest(word + "\n\n" + fourth_prompt));
    }


    public void makeExam(String originalFileUrl, int subject, int exampType, int questionType) {
        // 1. pdf ->  word -> text 변환
        // 2. 지문 추출
        List<String> passageList = new ArrayList<>();
        // 3. GPT => 문제 만들어줘
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();

        for (String passage : passageList) {
            String summary = first_question(passage);
            String word = second_question(summary);
            String script = third_question(summary);
            String answer = fourth_question(word);

            questionList.add(script);
            answerList.add(word);
            answerList.add(answer);
        }



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
