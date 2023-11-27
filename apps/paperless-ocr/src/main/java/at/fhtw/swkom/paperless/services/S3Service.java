package at.fhtw.swkom.paperless.services;

import java.io.InputStream;

public interface S3Service {
    InputStream getObject(String objectId);
}
