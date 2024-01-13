package at.fhtw.swkom.paperless.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentUploadTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldUploadDocumentSuccessfully() throws Exception {
        // Create a mock MultipartFile
        MockMultipartFile file = new MockMultipartFile(
                "document",
                "test.txt",
                "multipart/form-data",
                "Hello, World!".getBytes()
        );

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/documents/post_document/")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.task_id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("File uploaded successfully"))
                .andDo(print());
    }
}
