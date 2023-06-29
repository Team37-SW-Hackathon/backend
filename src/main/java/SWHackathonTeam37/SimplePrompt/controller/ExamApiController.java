package SWHackathonTeam37.SimplePrompt.controller;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.ExamRequest;
import SWHackathonTeam37.SimplePrompt.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
