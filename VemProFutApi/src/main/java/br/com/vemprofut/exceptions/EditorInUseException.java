package br.com.vemprofut.exceptions;

public class EditorInUseException extends RuntimeException {

  public EditorInUseException(String message) {
    super(message);
  }
}
