package at.fhtw.swkom.paperless.services;

import at.fhtw.swkom.paperless.services.dto.Document;
import co.elastic.clients.elasticsearch._types.Result;

import java.io.IOException;
import java.util.Optional;

public interface SearchIndexService {
    Result indexDocument(Document document) throws IOException;

    Optional<Document> getDocumentById(int id);
    boolean deleteDocumentById(int id);
}