package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class PaperlessMailMailrule {

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

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false, length = 256)
    private String folder;

    @Column(length = 256)
    private String filterFrom;

    @Column(length = 256)
    private String filterSubject;

    @Column(length = 256)
    private String filterBody;

    @Column(nullable = false)
    private Integer maximumAge;

    @Column(nullable = false)
    private Integer action;

    @Column(length = 256)
    private String actionParameter;

    @Column(nullable = false)
    private Integer assignTitleFrom;

    @Column(nullable = false)
    private Integer assignCorrespondentFrom;

    @Column(nullable = false, name = "\"order\"")
    private Integer order;

    @Column(nullable = false)
    private Integer attachmentType;

    @Column(length = 256)
    private String filterAttachmentFilename;

    @Column(nullable = false)
    private Integer consumptionScope;

    @Column(length = 256)
    private String filterTo;

}
