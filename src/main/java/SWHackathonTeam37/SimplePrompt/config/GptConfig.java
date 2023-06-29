package SWHackathonTeam37.SimplePrompt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
@Component
public class GptConfig {
    public static String AUTHORIZATION = "Authorization";
    public static String BEARER = "Bearer ";
    public static String API_KEY;
    public static String MODEL = "gpt-3.5-turbo";
    public static Integer MAX_TOKEN = 300;
    public static Double TEMPERATURE = 0.0;
    public static Double TOP_P = 1.0;
    public static final String ROLE = "user";
    public static String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static String URL = "https://api.openai.com/v1/chat/completions";

    @Value("${gpt-key}")
    public void setKey(String value) {
        API_KEY = value;
    }
}
