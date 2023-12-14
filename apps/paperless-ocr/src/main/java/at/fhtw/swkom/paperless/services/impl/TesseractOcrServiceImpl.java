package at.fhtw.swkom.paperless.services.impl;

import at.fhtw.swkom.paperless.config.RabbitMQConfig;
import at.fhtw.swkom.paperless.services.ContentService;
import at.fhtw.swkom.paperless.services.OcrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class TesseractOcrServiceImpl implements OcrService {

    private final String tesseractData;

    private final S3ServiceImpl s3Service;

    private final ContentService contentService;

    @Autowired
    public TesseractOcrServiceImpl(@Value("${tesseract.data}") String tessData, S3ServiceImpl s3Service, ContentService contentService) {
        this.tesseractData = tessData;
        this.s3Service = s3Service;
        this.contentService = contentService;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.OCR_OUT_QUEUE_NAME)
    public void processMessage(String message) throws JsonProcessingException {
        log.info("Received Message: " + message);

        // fetch document from minio
        InputStream inputStream = this.s3Service.getObject(message);

        // do OCR recognition
        String content = "";
        try {
            File tempFile = createTempFile(message, inputStream);
            content = doOCR(tempFile);

            if(content.isEmpty()){
                log.info("OCR returned empty string");
                return;
            }

            log.info(content);
        } catch (TesseractException | IOException e) {
            log.error(e.getMessage());
        }

        //save to DB
        this.contentService.save(Integer.parseInt(message), content);

        //TODO save to ElasticSearch
    }

    private String doOCR(File tempFile) throws TesseractException {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tesseractData);
        return tesseract.doOCR(tempFile);
    }

    private static File createTempFile(String fileName, InputStream is) throws IOException {
        File tempDir = new File("/tmp");

        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        File tempFile = File.createTempFile(fileName, ".pdf", tempDir);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);  //copy it to file system
            }
        }
        return tempFile;
    }
}
