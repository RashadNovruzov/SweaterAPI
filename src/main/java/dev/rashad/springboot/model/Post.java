package dev.rashad.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "post_text")
    private String postText;

    @ManyToMany
    @JoinTable(
            name = "Post_Tag",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
