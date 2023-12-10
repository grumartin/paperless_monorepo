package at.fhtw.swkom.paperless.services.impl;

import at.fhtw.swkom.paperless.persistence.entities.DocumentContent;
import at.fhtw.swkom.paperless.persistence.repos.DocumentContentRepository;
import at.fhtw.swkom.paperless.services.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContentServiceImpl implements ContentService {
    private final DocumentContentRepository documentContentRepository;

    @Autowired
    public ContentServiceImpl(DocumentContentRepository documentContentRepository) {
        this.documentContentRepository = documentContentRepository;
    }

    @Override
    public void save(Integer documentId, String content) {
        try {
            DocumentContent documentContent = new DocumentContent();
            documentContent.setDocumentId(documentId);
            documentContent.setContent(content);

            this.documentContentRepository.save(documentContent);
        } catch (DataAccessException ex) {
            log.error("Failed to save document content", ex);
            throw new RuntimeException("Failed to save document content", ex);
        } catch (Exception ex) {
            log.error("An unexpected error occurred", ex);
            throw new RuntimeException("An unexpected error occurred", ex);
        }
    }
}
