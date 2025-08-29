package file_upload_project.demo.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/multipart-file")
@RequiredArgsConstructor
public class FileUploadController {

    private final AmazonS3Client amazonS3Client;

    @PostMapping("/upload")
    public String uploadMultifile(
        @RequestPart MultipartFile file
    ) throws IOException {
        String originalFilename = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        PutObjectRequest putObjectRequest = new PutObjectRequest(
            "image-bucket-v2",
            originalFilename,
            file.getInputStream(),
            metadata
        );
        amazonS3Client.putObject(putObjectRequest);
        return amazonS3Client.getUrl("image-bucket-v2", originalFilename).toString();
    }
}
