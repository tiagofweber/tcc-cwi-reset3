package io.github.cwireset.tcc.exception.reserva;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PeriodoIndisponivelException extends RuntimeException {
    public PeriodoIndisponivelException() {
        super("Este anuncio já esta reservado para o período informado.");
    }
}
