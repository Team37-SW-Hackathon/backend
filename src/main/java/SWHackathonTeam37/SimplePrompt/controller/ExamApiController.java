package SWHackathonTeam37.SimplePrompt.controller;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.ExamRequest;
import SWHackathonTeam37.SimplePrompt.service.ExamService;
import SWHackathonTeam37.SimplePrompt.service.dto.response.ExamAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamApiController {
    private final ExamService examService;

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody @Valid ExamRequest request) {
        examService.makeExam(request.fileUrl(), request.subject(), request.examType(), request.questionType());
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<ExamAssembler> getList(@RequestBody int subject) { // 0 전체, 1 국어, 2 영어
        ExamAssembler response = examService.getList(subject);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<Long> idList) {
        examService.deleteList(idList);
        return ResponseEntity.ok().build();
    }
}
