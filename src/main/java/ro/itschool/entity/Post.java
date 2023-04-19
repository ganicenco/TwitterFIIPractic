package ro.itschool.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Post {
    @Id
    // can no longer use identity key generation, because of inheritance
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "post_id")
    private Long id;
    @Column(nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany //(mappedBy = "post")
    private Set<Likes> likes;

    @OneToMany (mappedBy = "post", cascade = ALL)
    private Set<Reply> replays = new LinkedHashSet<>();

    @OneToMany// (mappedBy = "post")
    private Set<Mention> mentions = new LinkedHashSet<>();

    public Post(String message, LocalDateTime timestamp, User user) {
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
    }
}
