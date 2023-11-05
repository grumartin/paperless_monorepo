package at.fhtw.swkom.paperless.mapping;

import at.fhtw.swkom.paperless.persistence.entities.DocumentsDocument;
import at.fhtw.swkom.paperless.persistence.entities.DocumentsDocumentTag;
import at.fhtw.swkom.paperless.persistence.entities.Storagepath;
import at.fhtw.swkom.paperless.persistence.entities.Tag;
import at.fhtw.swkom.paperless.persistence.entities.Documenttype;
import at.fhtw.swkom.paperless.services.dto.Correspondent;
import at.fhtw.swkom.paperless.services.dto.DocTag;
import at.fhtw.swkom.paperless.services.dto.Document;
import at.fhtw.swkom.paperless.services.dto.NewTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper
public interface DtoEntityMapper {
    @Mapping(target = "originalFileName", source = "originalFilename")
    @Mapping(target = "archivedFileName", source = "archiveFilename")
    Document documentEntityToDto(DocumentsDocument documentsDocument);
    DocumentsDocument documentDtoToEntity(Document document);
    default JsonNullable<Integer> map(Correspondent correspondent) {
        return correspondent!=null ? JsonNullable.of(Math.toIntExact(correspondent.getId())) : JsonNullable.undefined();
    }
    default JsonNullable<Integer> map(Documenttype documenttype) {
        return documenttype!=null ? JsonNullable.of(documenttype.getId()) : JsonNullable.undefined();
    }
    default JsonNullable<Integer> map(Storagepath storagepath) {
        return storagepath!=null ? JsonNullable.of(storagepath.getId()) : JsonNullable.undefined();
    }

    NewTag tagDtoToEntity(Tag tag);
    Tag tagEntityToTag(NewTag newTag);
//    DocTag docTagDtoToEntity(Tag tag);
//    Tag docTagEntityToDto(DocTag docTag);
    DocTag docTagDtoToEntity(DocumentsDocumentTag documentsDocumentTag);
    DocumentsDocumentTag docTagEntityToDto(DocTag docTag);
    Correspondent correspondentDtoToEntity(at.fhtw.swkom.paperless.persistence.entities.Correspondent correspondent);
    at.fhtw.swkom.paperless.persistence.entities.Correspondent correspondentEntityToDto(Correspondent correspondent);

}
