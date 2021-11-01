package io.github.cwireset.tcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdNaoEncontradoException extends RuntimeException {
    public IdNaoEncontradoException(Long id) {
        super(String.format("Nenhum(a) Usuario com Id com o valor '%d' foi encontrado.", id));
    }
}
