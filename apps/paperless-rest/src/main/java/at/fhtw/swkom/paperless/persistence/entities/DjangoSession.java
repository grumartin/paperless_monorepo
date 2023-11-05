package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DjangoSessions")
@Getter
@Setter
public class DjangoSession {

    @Id
    @Column(nullable = false, updatable = false, length = 40)
    private String sessionKey;

    @Column(nullable = false, columnDefinition = "text")
    private String sessionData;

    @Column(nullable = false)
    private OffsetDateTime expireDate;

}
