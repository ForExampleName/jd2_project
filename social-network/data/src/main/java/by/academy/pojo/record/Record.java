package by.academy.pojo.record;

import by.academy.pojo.account.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "record")
@NoArgsConstructor
@Getter
@Setter
public class Record {
    @Id
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @GeneratedValue(generator = "uuid-generator")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "header")
    private String header;

    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "picture", columnDefinition = "MEDIUMBLOB")
    private byte[] picture;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;
}
