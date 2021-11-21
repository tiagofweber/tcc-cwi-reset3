package io.github.cwireset.tcc.exception.reserva;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CancelamentoInvalidoException extends RuntimeException {
    public CancelamentoInvalidoException() {
        super("Não é possível realizar o cancelamento para esta reserva, pois ela não está no status PENDENTE.");
    }
}
