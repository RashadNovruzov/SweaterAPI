package dev.rashad.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {
  @Email
  @NotEmpty(message = "email can't be empty")
  @Size(max = 100)
  @NotNull
  private String email;

  @NotEmpty(message = "Password cant be empty")
  @NotNull
  private String password;
}
