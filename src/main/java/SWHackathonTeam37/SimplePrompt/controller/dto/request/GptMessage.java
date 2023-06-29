package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by kok8454@gmail.com on 2023-06-30
 * Github : http://github.com/perArdua
 */
@Getter
@NoArgsConstructor
public class GptMessage implements Serializable {
    private String role;
    private String content;

    @Builder
    public GptMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
