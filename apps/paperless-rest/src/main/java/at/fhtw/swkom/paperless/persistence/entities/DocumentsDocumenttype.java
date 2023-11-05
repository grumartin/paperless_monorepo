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
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DocumentsDocumenttypes")
@Getter
@Setter
public class DocumentsDocumenttype {

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
    private String name;

    @Column(nullable = false, length = 256)
    private String match;

    @Column(nullable = false)
    private Integer matchingAlgorithm;

    @Column(nullable = false)
    private Boolean isInsensitive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @OneToMany(mappedBy = "documentType")
    private Set<DocumentsDocument> documentTypeDocumentsDocuments;

    @Override
    public String toString() {
        return "DocumentsDocumenttype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", match='" + match + '\'' +
                ", matchingAlgorithm=" + matchingAlgorithm +
                ", isInsensitive=" + isInsensitive +
                ", owner=" + owner +
                ", documentTypeDocumentsDocuments=" + documentTypeDocumentsDocuments +
                '}';
    }
}
