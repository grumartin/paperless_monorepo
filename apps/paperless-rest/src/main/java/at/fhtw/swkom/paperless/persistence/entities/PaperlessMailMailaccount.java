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
@Table(name = "PaperlessMailMailaccounts")
@Getter
@Setter
public class PaperlessMailMailaccount {

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
    private String imapServer;

    @Column
    private Integer imapPort;

    @Column(nullable = false)
    private Integer imapSecurity;

    @Column(nullable = false, length = 256)
    private String username;

    @Column(nullable = false, length = 2048)
    private String password;

    @Column(nullable = false, length = 256)
    private String characterSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private AuthUser owner;

    @OneToMany(mappedBy = "account")
    private Set<PaperlessMailMailrule> accountPaperlessMailMailrules;

}
