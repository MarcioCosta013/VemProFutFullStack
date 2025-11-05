package br.com.vemprofut.exceptions;

public class FutInUseException extends RuntimeException{

    public FutInUseException(String message){
        super(message);
    }
}
