package at.fhtw.skom.paperless.mapping;

import at.fhtw.swkom.paperless.mapping.DtoEntityMapperImpl;
import at.fhtw.swkom.paperless.persistence.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.mapping.DtoEntityMapper;
//import at.fhtw.swkom.paperless.mapping.DtoEntityMapperImpl;
import at.fhtw.swkom.paperless.services.dto.Document;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DocumentMappingTest {
    @Test
    void testDocumentEntityToDto () {
        Integer id = 1;
        Integer correspondent = 2;
        Integer documentType = 3;
        Integer storagePath = 4;
        String title = "Title";
        String content = "Content";
        List<Integer> tags = List.of(100, 101);
        OffsetDateTime created = OffsetDateTime.of(LocalDate.of(2030, 1, 1), LocalTime.of(3, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime createdDate = OffsetDateTime.of(LocalDate.of(2020, 1, 1), LocalTime.of(2, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime modified = OffsetDateTime.of(LocalDate.of(2010, 1, 1), LocalTime.of(1, 0, 0), ZoneOffset.ofHours(1));
        OffsetDateTime added = OffsetDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.of(0, 0, 0), ZoneOffset.ofHours(1));
        String archiveSerialNumber = "5";
        String originalFileName = "OriginalFileName";
        String archivedFileName = "ArchivedFileName";

        DocumentsDocument document = new DocumentsDocument();
        document.setId(id);
        document.setTitle(title);
        document.setContent(content);
        document.setCreated(created);
        document.setModified(modified);
        document.setAdded(added);
        document.setArchiveSerialNumber(Integer.valueOf(archiveSerialNumber));
        document.setArchiveFilename(archivedFileName);
        document.setOriginalFilename(originalFileName);

        // TODO:
        /*
        String checksum
        String storageType
        String filename
        String mimeType
        String archiveChecksum
        DocumentsCorrespondent correspondent
        DocumentsDocumenttype documentType
        DocumentsStoragepath storagePath
        AuthUser owner
        Set<DocumentsNote> documentDocumentsNotes
        Set<DocumentsDocumentTags> documentDocumentsDocumentTagses
        */

        Document expectedDocumentDto = new Document();
        expectedDocumentDto.setId(id);
        expectedDocumentDto.setTitle(JsonNullable.of(title));
        expectedDocumentDto.setContent(JsonNullable.of(content));
        expectedDocumentDto.setCreated(created);
        expectedDocumentDto.setModified(modified);
        expectedDocumentDto.setAdded(added);
        expectedDocumentDto.setArchiveSerialNumber(JsonNullable.of(archiveSerialNumber));
        expectedDocumentDto.setArchivedFileName(JsonNullable.of(archivedFileName));
        expectedDocumentDto.setOriginalFileName(JsonNullable.of(originalFileName));

        // TODO:
        /*
        expectedDocumentDto.setCorrespondent(JsonNullable.of(correspondent));
        expectedDocumentDto.setDocumentType(JsonNullable.of(documentType));
        expectedDocumentDto.setStoragePath(JsonNullable.of(storagePath));
        expectedDocumentDto.setTags(JsonNullable.of(tags));
        expectedDocumentDto.setCreatedDate(createdDate);
         */

        DtoEntityMapperImpl documentMapperImpl = new DtoEntityMapperImpl();
        Document documentDto = documentMapperImpl.documentEntityToDto(document);

        System.out.println("Expected -----------------");
        System.out.println(expectedDocumentDto);

        System.out.println("Actual -----------------");
        System.out.println(documentDto);

        assertEquals(expectedDocumentDto, documentDto);
    }
}
