package by.academy.pojo.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    public User(String login, LocalDateTime creationTime) {
        this.login = login;
        this.creationTime = creationTime;
    }
}
