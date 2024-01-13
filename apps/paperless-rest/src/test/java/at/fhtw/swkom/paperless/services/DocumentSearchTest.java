package at.fhtw.swkom.paperless.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentSearchTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void uploadDocument() throws Exception {
        // Create a mock MultipartFile for a PDF file
        MockMultipartFile file = new MockMultipartFile(
                "document",
                "test.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                getClass().getResourceAsStream("../assets/test.pdf")
        );

        // Perform the POST request to upload the document
        String responseContent = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/documents/post_document/")
                        .file(file))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.task_id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("File uploaded successfully"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void searchForUploadedDocument() throws Exception {
        // Perform the GET request to search for the uploaded document by title content
        mockMvc.perform(MockMvcRequestBuilders.get("/api/documents/")
                        .param("page", "1")
                        .param("page_size", "10")
                        .param("title_content", "Hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.results").isArray())
                .andDo(print());
    }
}
