package at.fhtw.swkom.paperless.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Profile("s3-object-storage")
@Component
@Slf4j
public class S3Service implements at.fhtw.swkom.paperless.services.S3Service {
    @Autowired
    AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket.name}")
    String bucketName;

    @Value("${cloud.aws.s3.anonymous-files.enabled}")
    Boolean anonymousFilesEnabled;

    @Override
    @SneakyThrows
    public InputStream getObject(@NonNull String objectId) {
        InputStream is = null;
        S3Object s3Obj = null;

        try {
            s3Obj = this.s3Client.getObject(bucketName, objectId);

            try(S3ObjectInputStream stream = s3Obj.getObjectContent()) {
                ByteArrayOutputStream temp = new ByteArrayOutputStream();
                IOUtils.copy(stream, temp);
                is = new ByteArrayInputStream(temp.toByteArray());
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        } finally {
            if (s3Obj != null) {
                try {
                    // Close the object
                    s3Obj.close();
                } catch (IOException e) {
                    log.info("Unable to close S3 object: {}", e.getMessage(), e);
                }
            }
        }
        return is;
    }
}
