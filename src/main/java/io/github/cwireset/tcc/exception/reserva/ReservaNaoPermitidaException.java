package io.github.cwireset.tcc.exception.reserva;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservaNaoPermitidaException extends RuntimeException {
    public ReservaNaoPermitidaException() {
        super("O solicitante de uma reserva não pode ser o próprio anunciante.");
    }
}
