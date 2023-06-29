package SWHackathonTeam37.SimplePrompt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadApiController {

    @Value("${file.dir}")
    private String fileDir;

    public void saveFile(@RequestParam MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fullPath = UUID.randomUUID().toString() + "_" + fileDir + file.getOriginalFilename();
            file.transferTo(new File(fullPath));
        }
        return ;
    }
}
