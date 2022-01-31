package by.academy.pojo.account;


import by.academy.enums.UserRoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@Getter
@Setter
public class UserRole implements Serializable {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleType role;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    public UserRole(User user, UserRoleType role, LocalDateTime creationTime) {
        this.user = user;
        this.role = role;
        this.creationTime = creationTime;
    }
}
