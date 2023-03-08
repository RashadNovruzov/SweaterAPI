package dev.rashad.springboot.dto;

import dev.rashad.springboot.model.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponsePostDto {

    private String postText;

    private List<String> tagsTitle = new ArrayList<>();

    public ResponsePostDto(String postText, List<Tag> tags){
        this.postText = postText;
        tags.stream().forEach(t->tagsTitle.add(t.getTagTitle()));
    }
}
