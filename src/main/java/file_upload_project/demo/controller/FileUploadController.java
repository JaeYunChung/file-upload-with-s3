package file_upload_project.demo.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("upload")
public class FileUploadController {
    @PostMapping("multipart-file")
    public void uploadMultifile(
        @RequestPart MultipartFile file
    ) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        PutObjectRequest putObjectRequest = new PutObjectRequest(
            "image-bucket",
            "key",
            file.getInputStream(),
            metadata
        );
        AmazonS3Client amazonS3Client = new AmazonS3Client();
        amazonS3Client.putObject(putObjectRequest);
    }
}
