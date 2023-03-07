package dev.rashad.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tag_title")
    private String tagTitle;

    public Tag(String tagTitle){
        this.tagTitle = tagTitle;
    }

    @ManyToMany
    @JoinTable(
            name = "Post_Tag",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private List<Tag> posts;
}
