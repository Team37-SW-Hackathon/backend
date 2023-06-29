package SWHackathonTeam37.SimplePrompt.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by kok8454@gmail.com on 2023-06-29
 * Github : http://github.com/perArdua
 */
public class ApiKey {

    private static ApiKey apiKey;

    @Value("${gpt-key}")
    private String gptKey;

    @Value("${aws-access-key}")
    private String awsAccessKey;

    @Value("${aws-secret-key}")
    private String awsSecretKey;

    @Value("${aws-region}")
    private String awsRegion;

    public String getGptKey() {
        return gptKey;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    private ApiKey() {}

    public static ApiKey getInstance() {
        if (apiKey == null) {
            apiKey = new ApiKey();
        }
        return apiKey;
    }
}
