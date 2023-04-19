package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
public class Reply extends Post {

   // private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Post post;


}
