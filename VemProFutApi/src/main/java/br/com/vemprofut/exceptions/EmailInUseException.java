package br.com.vemprofut.exceptions;

public class EmailInUseException extends RuntimeException{

    public EmailInUseException(String mensagem){
        super(mensagem);
    }
}
