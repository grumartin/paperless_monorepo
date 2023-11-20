package at.fhtw.swkom.paperless.minio;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Profile("s3-object-storage")
@Component
public class DocumentStorage {
    @Autowired
    AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket.name}")
    String bucketName;

    @Value("${cloud.aws.s3.anonymous-files.enabled}")
    Boolean anonymousFilesEnabled;

    @SneakyThrows
    public void persistObject(@NonNull Integer id, @NonNull MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setHeader("originalFilename", file.getOriginalFilename());

        s3Client.putObject(bucketName,
                id.toString(),
                file.getInputStream(),
                metadata);
    }
}
