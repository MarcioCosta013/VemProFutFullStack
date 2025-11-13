package br.com.vemprofut.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/*
O Springdoc (biblioteca que gera o Swagger) scaneia todos os @RestControllerAdvice automaticamente.
Em algumas versões (principalmente Spring Boot 3.2+ com Springdoc 2.x+), há um bug que faz ele tentar
gerar documentação até para as classes de exception handler, e isso quebra o endpoint /v3/api-docs.
Ou seja: o Swagger está tentando documentar o GlobalExceptionHandler, o que não deveria acontecer.
 */
@Hidden // Isso impede o Swagger de tentar documentar essa classe
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

        logger.error("Erro nao tratado!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("erro", "Erro interno no servidor");
        body.put("detalhes", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<Map<String, Object>> handleEmailCadastrado(EmailInUseException ex){

        logger.warn("Email já cadastrado!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("erro", ex.getMessage());
            body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(FutInUseException.class)
    public ResponseEntity<Map<String, Object>> handleFutEmUso(FutInUseException ex){

        logger.warn("Nome de Fut já cadastrado!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("erro", ex.getMessage());
            body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(EditorInUseException.class)
    public ResponseEntity<Map<String, Object>> handleEditorJaIncluso(EditorInUseException ex){

        logger.warn("Editor já cadastrado!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("erro", ex.getMessage());
            body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NomeInUseException.class)
    public ResponseEntity<Map<String, Object>> handleNomeJaIncluso(NomeInUseException ex){

        logger.warn("Nome já em uso!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("erro", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex){

        logger.warn("Não encontrado!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("erro", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex){

        logger.warn("Recurso não encontrado!", ex.getMessage());

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("erro", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}
