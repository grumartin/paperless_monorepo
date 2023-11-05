package at.fhtw.swkom.paperless.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "AuthUsers")
@Getter
@Setter
public class AuthUser {

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
    private String password;

    @Column
    private OffsetDateTime lastLogin;

    @Column(nullable = false)
    private Boolean isSuperuser;

    @Column(nullable = false, length = 150)
    private String username;

    @Column(nullable = false, length = 150)
    private String firstName;

    @Column(nullable = false, length = 150)
    private String lastName;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(nullable = false)
    private Boolean isStaff;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private OffsetDateTime dateJoined;

    @OneToMany(mappedBy = "user")
    private Set<AuthUserGroups> userAuthUserGroups;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsCorrespondent> ownerDocumentsCorrespondents;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsDocumenttype> ownerDocumentsDocumenttypes;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsStoragepath> ownerDocumentsStoragepaths;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsTag> ownerDocumentsTags;

    @OneToMany(mappedBy = "user")
    private Set<DocumentsUisettings> userDocumentsUisettings;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsSavedview> ownerDocumentsSavedviews;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailaccount> ownerPaperlessMailMailaccounts;

    @OneToMany(mappedBy = "owner")
    private Set<DocumentsDocument> ownerDocumentsDocuments;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailMailrule> ownerPaperlessMailMailrules;

    @OneToMany(mappedBy = "user")
    private Set<AuthUserUserPermissions> userAuthUserUserPermissions;

    @OneToMany(mappedBy = "user")
    private Set<AuthtokenToken> userAuthtokenTokens;

    @OneToMany(mappedBy = "owner")
    private Set<PaperlessMailProcessedmail> ownerPaperlessMailProcessedmails;

}
