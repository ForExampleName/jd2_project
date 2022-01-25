package by.academy.pojo.account;

import by.academy.enums.AccountStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_status")
@NoArgsConstructor
@Getter
@Setter
public class UserStatus {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    public UserStatus(User user, AccountStatus status, LocalDateTime creationTime) {
        this.user = user;
        this.status = status;
        this.creationTime = creationTime;
    }
}
