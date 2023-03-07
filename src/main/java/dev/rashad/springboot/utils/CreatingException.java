package dev.rashad.springboot.utils;

import dev.rashad.springboot.exceptions.IncorrectData;
import dev.rashad.springboot.exceptions.UserNotFoundException;
import org.springframework.validation.BindingResult;

public abstract class CreatingException {

    public static void throwUserNotFoundException(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuffer message = new StringBuffer();
            bindingResult.getFieldErrors().stream().forEach(e->message.append(e.getField()+":"+e.getDefaultMessage()+"\n"));
            throw new UserNotFoundException(message.toString());
        }
    }

    public static void throwIncorrectDataException(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuffer message = new StringBuffer();
            bindingResult.getFieldErrors().stream().forEach(e->message.append(e.getField()+":"+e.getDefaultMessage()+"\n"));
            throw new IncorrectData(message.toString());
        }
    }

}
