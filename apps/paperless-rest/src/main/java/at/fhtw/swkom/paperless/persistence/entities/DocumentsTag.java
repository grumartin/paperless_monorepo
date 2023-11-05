package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DocumentsTags")
@Getter
@Setter
public class DocumentsTag {

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

    @Column(nullable = false)
    private Boolean isInboxTag;

    @Column(nullable = false, length = 7)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private AuthUser owner;

    @Override
    public String toString() {
        return "DocumentsTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", match='" + match + '\'' +
                ", matchingAlgorithm=" + matchingAlgorithm +
                ", isInsensitive=" + isInsensitive +
                ", isInboxTag=" + isInboxTag +
                ", color='" + color + '\'' +
                ", owner=" + owner +
                '}';
    }
}
