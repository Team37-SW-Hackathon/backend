package SWHackathonTeam37.SimplePrompt.controller.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record FileUploadRequest(
        MultipartFile multipartFile
) {
}
