package SWHackathonTeam37.SimplePrompt.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class FileUploadService {
    private final AmazonS3Client amazonS3Client;

    @Value( "${cloud.aws.s3.bucket}")
    private String S3Bucket;

    public String upload(MultipartFile multipartFile) throws IOException {
        String originalName = multipartFile.getOriginalFilename();
        long size = multipartFile.getSize();

        ObjectMetadata objectMetaData = new ObjectMetadata();
        objectMetaData.setContentType(multipartFile.getContentType());
        objectMetaData.setContentLength(size);

        amazonS3Client.putObject(
                new PutObjectRequest(S3Bucket, originalName, multipartFile.getInputStream(), objectMetaData)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        String fileUrl = amazonS3Client.getUrl(S3Bucket, originalName).toString();
        return fileUrl;
    }

    public String uploadByFileName(String fileName) throws IOException {
        File file = new File(fileName);
        String originalName = file.getName();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("application/octet-stream");
        objectMetadata.setContentLength(file.length());

        amazonS3Client.putObject(new PutObjectRequest(S3Bucket, originalName, new FileInputStream(file), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        String fileUrl = amazonS3Client.getUrl(S3Bucket, originalName).toString();
        return fileUrl;
    }
}
