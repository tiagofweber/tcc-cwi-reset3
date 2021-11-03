package io.github.cwireset.tcc.exception.anuncio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IdAnuncioNaoEncontradoException extends RuntimeException {
    public IdAnuncioNaoEncontradoException(Long id) {
        super(String.format("Nenhum(a) Anuncio com Id com o valor '%d' foi encontrado.", id));
    }
}
