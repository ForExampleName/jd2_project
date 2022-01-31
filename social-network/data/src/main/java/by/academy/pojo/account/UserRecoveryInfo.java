package by.academy.pojo.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_recovery")
@NoArgsConstructor
@Getter
@Setter
public class UserRecoveryInfo implements Serializable {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private SecretQuestion question;

    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    public UserRecoveryInfo(User user, SecretQuestion question, String answer, LocalDateTime creationTime) {
        this.user = user;
        this.question = question;
        this.answer = answer;
        this.creationTime = creationTime;
    }
}
