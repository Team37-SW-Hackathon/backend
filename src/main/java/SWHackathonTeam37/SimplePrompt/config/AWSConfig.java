package SWHackathonTeam37.SimplePrompt.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    private String accessKey;
    private String accessSecret;
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, accessSecret);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }

    // 여기 아래부분에 Value부분만 맞게 조절하시면 됩ㄴ디ㅏ
    @Value("${aws-access-key}")
    public void setAccessKey(String value) {
        accessKey = value;
    }

    @Value("${aws-secret-key}")
    public void setAccessSecret(String value) {
        accessSecret = value;
    }

    @Value("${aws-region}")
    public void setRegion(String value) {
        region = value;
    }
}