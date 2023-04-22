package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.itschool.enums.RoleName;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MyRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public MyRole(RoleName name) {
        this.name = name;
    }
}
