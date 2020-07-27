package entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "book")
@Getter @Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(of = "name")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn
    private Author author;


    public Book(String name, Author author) {
        if(name == null) {
            throw new NullPointerException();
        }
        this.name = name;
        this.author = author;
    }
}
