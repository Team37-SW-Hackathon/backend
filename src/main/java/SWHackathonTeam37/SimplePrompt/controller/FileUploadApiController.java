package SWHackathonTeam37.SimplePrompt.controller;

import SWHackathonTeam37.SimplePrompt.controller.dto.request.FileUploadRequest;
import SWHackathonTeam37.SimplePrompt.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fileUpload")
public class FileUploadApiController {
    private final FileUploadService fileUploadService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> fileUpload(@ModelAttribute @Valid FileUploadRequest request) throws IOException {
        String fileUrl = fileUploadService.upload(request.multipartFile());
        return ResponseEntity.ok(fileUrl);
    }
}
