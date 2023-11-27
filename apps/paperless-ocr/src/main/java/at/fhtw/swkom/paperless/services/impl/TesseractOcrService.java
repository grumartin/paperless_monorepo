package at.fhtw.swkom.paperless.services.impl;

import at.fhtw.swkom.paperless.config.RabbitMQConfig;
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
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class TesseractOcrService implements OcrService {

    private final String tesseractData;

    private final S3Service s3Service;

    @Autowired
    public TesseractOcrService(@Value("${tesseract.data}") String tessData, S3Service s3Service) {
        this.tesseractData = tessData;
        this.s3Service = s3Service;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.OCR_OUT_QUEUE_NAME)
    public void processMessage(String message) throws JsonProcessingException {
        log.info("Received Message: " + message);

        // fetch document from minio
        InputStream inputStream = this.s3Service.getObject(message);

        // do OCR recognition
        try {
            File tempFile = createTempFile(message, inputStream);
            String result = doOCR(tempFile);
            log.info(result);
        } catch (TesseractException | IOException e) {
            log.error(e.getMessage());
        }
    }

    private String doOCR(File tempFile) throws TesseractException {
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(tesseractData);
        return tesseract.doOCR(tempFile);
    }

    private static File createTempFile(String fileName, InputStream is) throws IOException {
        File tempFile = File.createTempFile(StringUtils.getFilename(fileName), ".pdf");
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
