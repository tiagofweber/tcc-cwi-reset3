package io.github.cwireset.tcc.exception.reserva;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstornoInvalidoException extends RuntimeException {
    public EstornoInvalidoException() {
        super("Não é possível realizar o estorno para esta reserva, pois ela não está no status PAGO.");
    }
}
