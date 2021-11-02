package io.github.cwireset.tcc.exception.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdUsuarioNaoEncontradoException extends RuntimeException {
    public IdUsuarioNaoEncontradoException(Long id) {
        super(String.format("Nenhum(a) Usuario com Id com o valor '%d' foi encontrado.", id));
    }
}
