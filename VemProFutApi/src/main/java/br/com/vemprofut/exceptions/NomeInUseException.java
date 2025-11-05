package br.com.vemprofut.exceptions;

public class NomeInUseException extends RuntimeException{

    public NomeInUseException (String message){
        super(message);
    }
}
