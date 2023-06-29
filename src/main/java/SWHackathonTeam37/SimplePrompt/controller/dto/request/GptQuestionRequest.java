package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
@Getter
@NoArgsConstructor

public class GptQuestionRequest implements Serializable {
        private String question;
}
