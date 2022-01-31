package by.academy.pojo.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "authentication_info")
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationInfo implements Serializable {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    public AuthenticationInfo(User user, String password, LocalDateTime creationTime) {
        this.user = user;
        this.password = password;
        this.creationTime = creationTime;
    }
}