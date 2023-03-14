package dev.rashad.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentDto {

    private String text;

    private String authorName;

    private Integer authorId;

}
