package io.github.cwireset.tcc.exception.reserva;

import io.github.cwireset.tcc.domain.TipoImovel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeMinimaDePessoasEmHotelInvalidaException extends RuntimeException {
    public QuantidadeMinimaDePessoasEmHotelInvalidaException() {
        super("Não é possivel realizar uma reserva com menos de 2 pessoas para imóveis do tipo Hotel");
    }
}
