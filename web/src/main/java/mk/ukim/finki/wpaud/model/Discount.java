package mk.ukim.finki.wpaud.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    private LocalDateTime validUntil;

    @ManyToMany
    private List<User> users;

    public Discount() {}

    public Discount(LocalDateTime validUntil) {
        this.dateCreated = LocalDateTime.now();
        this.validUntil = validUntil;
        this.users = new ArrayList<>();
    }
}
