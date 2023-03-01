package dev.rashad.springboot.utils;

import dev.rashad.springboot.dto.RegisterRequestDto;
import dev.rashad.springboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class RegisterValidator implements Validator {
    private final UserRepository userRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterRequestDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequestDto registerRequestDto = (RegisterRequestDto) target;
        if(!registerRequestDto.getPassword().equals(registerRequestDto.getPasswordAgain())){
            errors.rejectValue("passwordAgain","","Passwords have to be same");
            return;
        }
        if(userRepository.findByEmail(registerRequestDto.getEmail()).isPresent()){
            errors.rejectValue("email","","User with this email already exists");
        }
    }
}
