package dev.rashad.springboot.controller;

import dev.rashad.springboot.dto.ExceptionResponseDto;
import dev.rashad.springboot.exceptions.IncorrectData;
import dev.rashad.springboot.exceptions.PostNotFoundException;
import dev.rashad.springboot.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(UserNotFoundException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getMessage());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(IncorrectData e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getMessage());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDto> exceptionHandler(PostNotFoundException e){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(e.getMessage());
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }

}
