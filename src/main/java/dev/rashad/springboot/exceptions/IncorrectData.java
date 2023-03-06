package dev.rashad.springboot.exceptions;

public class IncorrectData extends RuntimeException{
    public IncorrectData(String msg){
        super(msg);
    }
}
