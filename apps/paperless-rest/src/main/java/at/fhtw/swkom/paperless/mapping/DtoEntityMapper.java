package at.fhtw.swkom.paperless.mapping;

import at.fhtw.swkom.paperless.persistence.entities.*;
import at.fhtw.swkom.paperless.services.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper
public interface DtoEntityMapper extends JsonNullableMapper {
    @Mapping(target = "originalFileName", source = "originalFileName")
    @Mapping(target = "archivedFileName", source = "archiveFilename")
    Document entityToDto(DocumentsDocument documentsDocument);
    DocumentsDocument dtoToEntity(Document documentDto);

    default JsonNullable<Integer> map(DocumentsCorrespondent documentsCorrespondent) {
        return documentsCorrespondent!=null ? JsonNullable.of(documentsCorrespondent.getId()) : JsonNullable.undefined();
    }
    default JsonNullable<Integer> map(DocumentsDocumenttype documentsDocumenttype) {
        return documentsDocumenttype!=null ? JsonNullable.of(documentsDocumenttype.getId()) : JsonNullable.undefined();
    }
    default JsonNullable<Integer> map(DocumentsStoragepath documentsStoragepath) {
        return documentsStoragepath!=null ? JsonNullable.of(documentsStoragepath.getId()) : JsonNullable.undefined();
    }

    //Document Tags
    DocTag entityToDto(DocumentsTag documentsTag);
    DocumentsTag dtoToEntity(DocTag docTag);

    //Document Types
    DocumentType entityToDto(DocumentsDocumenttype documentType);
    DocumentsDocumenttype dtoToEntity(DocumentType documentType);

    //Document Corespondent:
    //entity(DocumentCorrespondent) -> dto(Corespondent)
    //dto(Correspondent) -> entity(DocumentCorrespondent)
    Correspondent entityToDto(DocumentsCorrespondent correspondent);
    DocumentsCorrespondent dtoToEntity(Correspondent correspondent);

}
