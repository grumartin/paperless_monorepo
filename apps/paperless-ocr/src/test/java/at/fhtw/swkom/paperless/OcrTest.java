package at.fhtw.swkom.paperless;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

public class OcrTest {

    @Test
    public void testOCR() {
        // Replace 'path/to/your/image.png' with the actual path to your test image
        File imageFile = new File("/usr/share/tessdata");

        // Initialize Tesseract instance
        ITesseract tesseract = new Tesseract();
        // Set the path to Tesseract executable (this is optional if Tesseract is in your system's PATH)
        tesseract.setDatapath(LoadLibs.extractTessResources("tessdata").getAbsolutePath());

        try {
            // Perform OCR on the image
            String result = tesseract.doOCR(imageFile);
            System.out.println(result);

            // Expected result based on the content of your test image
            String expectedText = "Das ist ein Test.";

            // Assert that the OCR result matches the expected result
            assertEquals(expectedText.trim(), result.trim());
        } catch (Exception e) {
            // Handle any exceptions, e.g., TesseractNotFoundException or IOException
            e.printStackTrace();
        }
    }
}

