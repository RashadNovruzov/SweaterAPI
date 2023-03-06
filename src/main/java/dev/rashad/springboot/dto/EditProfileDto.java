package dev.rashad.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditProfileDto {
    @NotEmpty
    @Size(min = 1,max = 30)
    @NotNull
    private String username;
    private String about;
}
