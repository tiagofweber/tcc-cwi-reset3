package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfDuplicadoException extends RuntimeException {
    public CpfDuplicadoException(String cpf) {
        super(String.format("Já existe um recurso do tipo Usuario com CPF com o valor '%s'.", cpf));
    }
}
