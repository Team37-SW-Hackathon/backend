package SWHackathonTeam37.SimplePrompt.controller.dto.response;

import SWHackathonTeam37.SimplePrompt.domain.vo.Choice;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
public record GptResponse (
        String id,
        String object,
        LocalDate created,
        String model,
        List<Choice> choiceList
){}
