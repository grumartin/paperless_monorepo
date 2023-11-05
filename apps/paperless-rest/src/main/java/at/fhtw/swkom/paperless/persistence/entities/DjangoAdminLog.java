package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DjangoAdminLogs")
@Getter
@Setter
public class DjangoAdminLog {

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

    @Column(nullable = false)
    private OffsetDateTime actionTime;

    @Column(columnDefinition = "text")
    private String objectId;

    @Column(nullable = false, length = 200)
    private String objectRepr;

    @Column(nullable = false)
    private Integer actionFlag;

    @Column(nullable = false, columnDefinition = "text")
    private String changeMessage;

    @Column
    private Integer contentTypeId;

    @Column(nullable = false)
    private Integer userId;

}
