package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
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
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private OffsetDateTime created;

    @Column(nullable = false)
    private OffsetDateTime modified;

    @Column(nullable = false, length = 32)
    private String checksum;

    @Column(nullable = false)
    private OffsetDateTime added;

    @Column(nullable = false, length = 11)
    private String storageType;

    @Column(length = 1024)
    private String filename;

    @Column
    private Integer archiveSerialNumber;

    @Column(nullable = false, length = 256)
    private String mimeType;

    @Column(length = 32)
    private String archiveChecksum;

    @Column(length = 1024)
    private String archiveFilename;

    @Column(length = 1024)
    private String originalFilename;

}
