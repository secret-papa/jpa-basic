package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roletype;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
//    private LocalDate createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
//    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member() {}

    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoleType(RoleType roletype) {
        this.roletype = roletype;
    }
}
