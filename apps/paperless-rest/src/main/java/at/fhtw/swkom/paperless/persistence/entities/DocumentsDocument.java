package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DocumentsDocuments")
@Getter
@Setter
public class DocumentsDocument {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private OffsetDateTime created;

    @Column(nullable = false)
    private OffsetDateTime modified;

    @Column(nullable = false)
    private OffsetDateTime added;

    @Column(nullable = false, length = 11)
    private String storageType;

    @Column(length = 1024)
    private String filename;

    @Column
    private Integer archiveSerialNumber;

    @Column(length = 32)
    private String archiveChecksum;

    @Column(length = 1024)
    private String archiveFilename;

    @Column(length = 1024)
    private String originalFileName;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "correspondent_id")
    private DocumentsCorrespondent correspondent;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "document_type_id")
    private DocumentsDocumenttype documentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "storage_path_id")
    private DocumentsStoragepath storagePath;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsNote> documentDocumentsNotes;

    @OneToMany(mappedBy = "document")
    private Set<DocumentsDocumentTags> documentDocumentsDocumentTags;

    @Override
    public String toString() {
        return "DocumentsDocument{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", added=" + added +
                ", storageType='" + storageType + '\'' +
                ", filename='" + filename + '\'' +
                ", archiveSerialNumber=" + archiveSerialNumber +
                ", archiveChecksum='" + archiveChecksum + '\'' +
                ", archiveFilename='" + archiveFilename + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", correspondent=" + correspondent +
                ", documentType=" + documentType +
                ", storagePath=" + storagePath +
                ", owner=" + owner +
                '}';
    }
}
