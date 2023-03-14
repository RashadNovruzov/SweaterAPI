package dev.rashad.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comment")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User author;
}
