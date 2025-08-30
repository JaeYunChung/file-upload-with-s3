package file_upload_project.demo.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@RestController
@RequestMapping("/multipart-file")
@RequiredArgsConstructor
public class FileUploadController {

    private final S3Client s3Client;

    @PostMapping("/upload")
    public String uploadMultifile(
        @RequestPart MultipartFile file
    ) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String bucketName = "image-bucket-v2";
        
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(originalFilename)
            .contentType(file.getContentType())
            .contentLength(file.getSize())
            .build();
            
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        
        return String.format("https://%s.s3.amazonaws.com/%s", bucketName, originalFilename);
    }
}
