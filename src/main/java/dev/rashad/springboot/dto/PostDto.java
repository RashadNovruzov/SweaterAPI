package dev.rashad.springboot.dto;

import dev.rashad.springboot.model.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotEmpty
    @NotNull
    @Size(min = 1,max = 500)
    private String postText;

    @Size(min = 2,max = 50)
    private String tags;
}
