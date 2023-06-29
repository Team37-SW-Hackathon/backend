package SWHackathonTeam37.SimplePrompt.service;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptQuestionRequest;
import SWHackathonTeam37.SimplePrompt.domain.Exam;
import SWHackathonTeam37.SimplePrompt.domain.ExamRepository;
import SWHackathonTeam37.SimplePrompt.service.dto.response.ExamAssembler;
import SWHackathonTeam37.SimplePrompt.service.dto.response.SimpleExam;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final GptService gptService;
    private final FileUploadService fileUploadService;

    public List<String> passageList;
    public List<String> answerList;
    public List<String> questionList;
    public static final String first_prompt_1 = "위 영어지문을 한 문장, 10단어 이내로 영어로 요약하세요\n.json 타입으로 key값은 summary를 사용하세요.";
    public static final String second_prompt_1 = "위 요약문에서 핵심 단어 하나를 Json타입으로 작성하세요. key 값은 \"word\"를 사용하세요";
    public static final String third_prompt_1 = "위 요약문에서 {word} 단어를 지우고 그 위치에 @@@기호를 넣어서 새 요약문을 Json타입으로 작성하세요. key 값은 \"script\"를 사용하세요.\n";
    public static final String fourth_prompt_1 = "{word} 단어와 거리가 먼 단어 세 개를 작성하세요. key값은 \"1,2,3\"을 사용하세요.";

    public static final String first_prompt_2 = "위 영어지문에서 하나만 있는 단어를 하나 json 형식으로 작성하세요. key는 \"word\"를 사용하세요";
    public static final String second_prompt_2 = "위 영어지문에서 {word}를 다른 단어로 바꿔서 script를 작성하세요. changed_word와 script를 json 형식으로 작성하세요.\n";
    public static final String third_prompt_2 = "changed_word를 제외한 지문 내에 있는 임의의 단어 3개를 json 형식으로 작성하세요. key는 \"1,2,3\"을 사용하세요.";


    public static final String first_prompt_3 = "위 영어 지문에 대해서 주제 선택하는 객관식 문제를 1 개 작성하세요.\n" +
            "위 영어 지문에 대해서 요지 선택하는 객관식 문제를 1 개 작성하세요.\n" +
            "위 영어 지문에 대해서 제목을 고르는 객관식 문제를 1 개 작성하세요.\n";
    public static final String second_prompt_3 = "각 문제는 question, options , answer, explanation이 key인 json 타입으로 작성하세요.\n" +
            "\n" +
            "options의 key는\"1,2,3,4\"로 작성하세요.\n" +
            "\n" +
            "문제가 여러개라면 array 타입으로 감싸세요.\n" +
            "\n" +
            "explanation은 3문장 이상 상세하게 설명하세요.";


    public String first_question_1(String passage) {
        return gptService.getAnswer(new GptQuestionRequest(passage + "\n\n" + first_prompt_1));
    }

    public String second_question_1(String summary) {
        return gptService.getAnswer(new GptQuestionRequest(summary + "\n\n" + second_prompt_1));
    }

    public String third_question_1(String summary) {
        return gptService.getAnswer(new GptQuestionRequest(summary + "\n\n" + third_prompt_1));
    }
    public String fourth_question_1(String word) {
        return gptService.getAnswer(new GptQuestionRequest(word + "\n\n" + fourth_prompt_1));
    }

    public String first_question_2(String string) {
        return gptService.getAnswer(new GptQuestionRequest(string + "\n\n" + first_prompt_2));
    }

    public String second_question_2(String string) {
        return gptService.getAnswer(new GptQuestionRequest(string + "\n\n" + second_prompt_2));
    }

    public String third_question_2(String string) {
        return gptService.getAnswer(new GptQuestionRequest(string + "\n\n" + third_prompt_2));
    }

    public String first_question_3(String string) {
        return gptService.getAnswer(new GptQuestionRequest(string + "\n\n" + first_prompt_3 + "\n" + second_prompt_3));
    }

    public void type_1(String passage) {
        List<String> wrong_answer = new ArrayList<>();

        String summary = first_question_1(passage);
        String word = second_question_1(summary);
        String script = third_question_1(summary);
        String answer = fourth_question_1(word);

        JSONObject jObject1 = new JSONObject(summary);
        summary = jObject1.getString("summary");

        JSONObject jObject2 = new JSONObject(summary);
        word = jObject2.getString("word");

        JSONObject jObject3 = new JSONObject(script);
        script = jObject3.getString("script");

        JSONObject jObject4 = new JSONObject(answer);
        wrong_answer.add(jObject4.getString("1"));
        wrong_answer.add(jObject4.getString("2"));
        wrong_answer.add(jObject4.getString("3"));
        wrong_answer.add(word);

        Collections.shuffle(wrong_answer);

        questionList.add(script);
        questionList.add("\n1. " + wrong_answer.get(0) + "\n2. " + wrong_answer.get(1) + "\n3. " + wrong_answer.get(2) + "\n4. " + wrong_answer.get(3) + "\n\n");
        answerList.add(word);
    }

    public void type_2 (String passage) {
        ArrayList<String> wrong_answer = new ArrayList<>();

        String ret1 = first_question_2(passage);
        String ret2 = second_question_2(passage);
        String ret3 = third_question_2(passage);

        JSONObject jObject1 = new JSONObject(ret1);
        String word = jObject1.getString("word");

        JSONObject jObject2 = new JSONObject(ret2);
        String changed_word = jObject2.getString("changed_word");
        String scipt = jObject2.getString("script");

        JSONObject jObject3 = new JSONObject(ret3);
        wrong_answer.add(jObject3.getString("1"));
        wrong_answer.add(jObject3.getString("2"));
        wrong_answer.add(jObject3.getString("3"));
        wrong_answer.add(changed_word);

        Collections.shuffle(wrong_answer);
        questionList.add(scipt);
        questionList.add("\n1. " + wrong_answer.get(0) + "\n2. " + wrong_answer.get(1) + "\n3. " + wrong_answer.get(2) + "\n4. " + wrong_answer.get(3) + "\n\n");
        answerList.add(changed_word);
    }

    public void type_3 (String passage) {
        String ret =  gptService.getAnswer(new GptQuestionRequest(passage + "\n\n" + "위 영어 지문에 대해서 주제 선택하는 객관식 문제를 1 개 작성하세요.\n" + "문제는 question, options , answer, explanation이 key인 json 타입으로 작성하세요." + "options의 key는\"1,2,3,4\"로 작성하세요.\n" +
                "\n" +
                "문제가 여러개라면 array 타입으로 감싸세요.\n" +
                "\n" +
                "explanation은 3문장 이상 상세하게 설명하세요."));

        JSONArray jsonArray = new JSONArray(ret);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String question = jsonObject.getString("question");
            JSONObject options = jsonObject.getJSONObject("options");
            String answer = jsonObject.getString("answer");
            String explanation = jsonObject.getString("explanation");

            explanation = gptService.getAnswer(new GptQuestionRequest(explanation + "\n\n" + "위의 문장을 한국말로 번역해주세요."));
            questionList.add(question + "\n" + explanation + "\n\n" + "\n1. " + options.getString("1") + "\n2. " + options.getString("2") + "\n3. " + options.getString("3") + "\n4. " + options.getString("4") + "\n\n");
            answerList.add(answer);
        }
    }

    public void type_4 (String passage) {
        String ret =  gptService.getAnswer(new GptQuestionRequest(passage + "\n\n" + "위 영어 지문에 대해서 요지 선택하는 객관식 문제를 1 개 작성하세요.\n" +
                "\n" +
                "각 문제는 question, options , answer, explanation이 key인 json 타입으로 작성하세요." +
                "\n" +
                "문제가 여러개라면 array 타입으로 감싸세요.\n" +
                "\n" +
                "explanation은 3문장 이상 상세하게 설명하세요."));

        JSONArray jsonArray = new JSONArray(ret);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String question = jsonObject.getString("question");
            JSONObject options = jsonObject.getJSONObject("options");
            String answer = jsonObject.getString("answer");
            String explanation = jsonObject.getString("explanation");

            explanation = gptService.getAnswer(new GptQuestionRequest(explanation + "\n\n" + "위의 문장을 한국말로 번역해주세요."));
            questionList.add(question + "\n" + explanation + "\n\n" + "\n1. " + options.getString("1") + "\n2. " + options.getString("2") + "\n3. " + options.getString("3") + "\n4. " + options.getString("4") + "\n\n");
            answerList.add(answer);
        }
    }

    public void type_5 (String passage) {
        String ret =  gptService.getAnswer(new GptQuestionRequest(passage + "\n\n" + "위 영어 지문에 대해서 제목을 고르는 객관식 문제를 1 개 작성하세요.\n" +
                "\n" +
                "각 문제는 question, options , answer, explanation이 key인 json 타입으로 작성하세요." +
                "\n" +
                "문제가 여러개라면 array 타입으로 감싸세요.\n" +
                "\n" +
                "explanation은 3문장 이상 상세하게 설명하세요."));

        JSONArray jsonArray = new JSONArray(ret);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String question = jsonObject.getString("question");
            JSONObject options = jsonObject.getJSONObject("options");
            String answer = jsonObject.getString("answer");
            String explanation = jsonObject.getString("explanation");

            explanation = gptService.getAnswer(new GptQuestionRequest(explanation + "\n\n" + "위의 문장을 한국말로 번역해주세요."));
            questionList.add(question + "\n" + explanation + "\n\n" + "\n1. " + options.getString("1") + "\n2. " + options.getString("2") + "\n3. " + options.getString("3") + "\n4. " + options.getString("4") + "\n\n");
            answerList.add(answer);
        }
    }


    public void makeExam(String originalFileUrl, int subject, List<Integer> questionType, int startPage, int endPage) throws IOException {
        // 1. pdf ->  word -> text 변환
        // 2. 지문 추출

        passageList = new ArrayList<>();
        // 3. GPT => 문제 만들어줘
        questionList = new ArrayList<>();
        answerList = new ArrayList<>();

        int circle = 1;

        for (String passage : passageList) {
            if (questionType.contains(circle)) {
                if (circle == 1) {
                    type_1(passage);
                } else if (circle == 2) {
                    type_2(passage);
                } else if (circle == 3) {
                    type_3(passage);
                } else if (circle == 4) {
                    type_4(passage);
                } else if (circle == 5) {
                    type_5(passage);
                }
            }
            circle += 1;
            circle %= 5;
        }
        
        // 4. 문제를 파일로

        String examFileName = createDocument(passageList, questionList);
        String answerFileName = createDocument(questionList, answerList);

        String examFileURL = fileUploadService.uploadByFileName(examFileName);
        String answerFileURL = fileUploadService.uploadByFileName(answerFileName);

        Exam entity = Exam.builder()
                .subject(subject)
                .originalFileUrl(originalFileUrl)
                .examFileUrl(examFileURL)
                .answerFileUrl(answerFileURL)
                .examFileName(examFileName)
                .build();
        examRepository.save(entity);
    }

    public String createDocument(List<String> passageList, List<String> questionList) {
        XWPFDocument document = new XWPFDocument();

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        String result = "";
        for (int i = 0; i < passageList.size(); i++) {
            result += Integer.toString(i+1) + passageList.get(i) + "\n" + questionList.get(i) + "\n\n";
        }

        run.setText(result);

        String fileName = "시험지" + Math.random() * 10000 +".docx";
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            document.write(outputStream);
            System.out.println("Word 파일이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
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

    public List<String> workbook1(String doc){
        String[] workbook = doc.split(" ");
        List<String> list = Arrays.asList(workbook);
        Collections.shuffle(list);
        return list;
    }

    public List<String> workbook2(String doc) {
        String[] workbook = doc.split(".");
        List<String> list =Arrays.asList(workbook);
        return list;
    }

    public List<String> workbook3(String doc) {
        String[] var = doc.split(".");
        int len = var.length;

        List<String> workbook = new ArrayList<String>();
        String sentence = "";
        if (len > 1) {
            for (int i = 1 ; i < len / 3 ; i++) {
                sentence += var[i];
            }
            workbook.add(sentence);
            sentence = "";
            for (int i = len / 3 ; i < 2 * len / 3; i++) {
                sentence += var[i];
            }
            workbook.add(sentence);
            sentence = "";
            for(int i = 2 * len / 3 ; i < len; i++) {
                sentence += var[i];
            }
            workbook.add(sentence);
        }

        Collections.shuffle(workbook);
        return workbook;
    }
}