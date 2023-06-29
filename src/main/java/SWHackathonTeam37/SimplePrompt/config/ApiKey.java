package SWHackathonTeam37.SimplePrompt.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
public class ApiKey {

    @Value("${gpt-key}")
    private String gptKey;

    public String getGptKey() {
        return gptKey;
    }
}
