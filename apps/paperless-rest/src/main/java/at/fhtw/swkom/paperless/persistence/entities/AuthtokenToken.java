package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class AuthtokenToken {

    @Id
    @Column(nullable = false, updatable = false, length = 40)
    private String aKey;

    @Column(nullable = false)
    private OffsetDateTime created;

}
