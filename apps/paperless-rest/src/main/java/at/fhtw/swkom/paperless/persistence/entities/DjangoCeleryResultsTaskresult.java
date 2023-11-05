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
@Table(name = "DjangoCeleryResultsTaskresults")
@Getter
@Setter
public class DjangoCeleryResultsTaskresult {

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

    @Column(nullable = false, unique = true)
    private String taskId;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = false, length = 128)
    private String contentType;

    @Column(nullable = false, length = 64)
    private String contentEncoding;

    @Column(columnDefinition = "text")
    private String result;

    @Column(nullable = false)
    private OffsetDateTime dateDone;

    @Column(columnDefinition = "text")
    private String traceback;

    @Column(columnDefinition = "text")
    private String meta;

    @Column(columnDefinition = "text")
    private String taskArgs;

    @Column(columnDefinition = "text")
    private String taskKwargs;

    @Column
    private String taskName;

    @Column(length = 100)
    private String worker;

    @Column(nullable = false)
    private OffsetDateTime dateCreated;

    @Column
    private String periodicTaskName;

}
