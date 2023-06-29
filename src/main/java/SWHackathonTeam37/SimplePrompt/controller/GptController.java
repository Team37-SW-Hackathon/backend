package SWHackathonTeam37.SimplePrompt.controller;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.GptQuestionRequest;
import SWHackathonTeam37.SimplePrompt.controller.dto.response.GptResponse;
import SWHackathonTeam37.SimplePrompt.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
@RestController
@RequestMapping("/gpts")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @PostMapping("/questions")
    public GptResponse sendRequest(@RequestBody @Valid GptQuestionRequest request) {
        return gptService.askRequest(request);
    }
}
