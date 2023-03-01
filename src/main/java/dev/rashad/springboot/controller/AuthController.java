package dev.rashad.springboot.controller;

import dev.rashad.springboot.dto.LoginRequestDto;
import dev.rashad.springboot.exceptions.UserNotFoundException;
import dev.rashad.springboot.service.AuthService;
import dev.rashad.springboot.utils.RegisterValidator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rashad.springboot.dto.AuthResponseDto;
import dev.rashad.springboot.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;
  private final RegisterValidator registerValidator;

  @PostMapping
  public ResponseEntity<AuthResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
      StringBuffer message = new StringBuffer();
      bindingResult.getFieldErrors().stream().forEach(e->message.append(e.getField()+":"+e.getDefaultMessage()+"\n"));
      throw new UserNotFoundException(message.toString());
    }
    return ResponseEntity.ok(authService.login(loginRequestDto));
  }
  
  @PostMapping("/register")
  public ResponseEntity<AuthResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto, BindingResult bindingResult) {
    registerValidator.validate(registerRequestDto,bindingResult);
    if (bindingResult.hasErrors()){
      StringBuffer message = new StringBuffer();
      bindingResult.getFieldErrors().stream().forEach(e->message.append(e.getField()+":"+e.getDefaultMessage()+"\n"));
      throw new UserNotFoundException(message.toString());
    }
    return ResponseEntity.ok(authService.register(registerRequestDto));
  }
}
