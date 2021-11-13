package io.github.cwireset.tcc.exception.reserva;

import io.github.cwireset.tcc.domain.FormaPagamento;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormaDePagamentoNaoAceitaException extends RuntimeException {
    public FormaDePagamentoNaoAceitaException(FormaPagamento formaPagamento, String formasAceitas) {
        super(String.format("O anúncio não aceita %s como forma de pagamento. As formas aceitas são %s.", formaPagamento, formasAceitas));
    }
}
