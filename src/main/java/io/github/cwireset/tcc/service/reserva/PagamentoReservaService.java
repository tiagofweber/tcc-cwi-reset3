package io.github.cwireset.tcc.service.reserva;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.domain.StatusPagamento;
import io.github.cwireset.tcc.exception.reserva.EstornoInvalidoException;
import io.github.cwireset.tcc.exception.reserva.FormaDePagamentoNaoAceitaException;
import io.github.cwireset.tcc.exception.reserva.PagamentoInvalidoException;
import io.github.cwireset.tcc.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private BuscaReservaService buscaReservaService;

    public void pagarReserva(Long idReserva, FormaPagamento formaPagamento) {
        Reserva reserva = buscaReservaService.buscarReservaPorId(idReserva);

        String formasDePagamentoAceitasPeloAnuncio = converterListParaString(reserva.getAnuncio().getFormasAceitas());

        boolean formaDePagamentoAceita = reserva.getAnuncio().getFormasAceitas().contains(formaPagamento);

        if (!formaDePagamentoAceita)
            throw new FormaDePagamentoNaoAceitaException(formaPagamento, formasDePagamentoAceitasPeloAnuncio);

        if (reserva.getPagamento().getStatus() != StatusPagamento.PENDENTE)
            throw new PagamentoInvalidoException();

        reserva.getPagamento().setFormaEscolhida(formaPagamento);
        reserva.getPagamento().setStatus(StatusPagamento.PAGO);
        reservaRepository.save(reserva);
    }

    public void estornarReserva(Long idReserva) {
        Reserva reserva = buscaReservaService.buscarReservaPorId(idReserva);

        if (reserva.getPagamento().getStatus() != StatusPagamento.PAGO)
            throw new EstornoInvalidoException();

        reserva.getPagamento().setStatus(StatusPagamento.ESTORNADO);
        reserva.getPagamento().setFormaEscolhida(null);
        reservaRepository.save(reserva);
    }

    private String converterListParaString(List<FormaPagamento> formasAceitas) {
        String str = "";
        for (FormaPagamento formaAceita : formasAceitas) {
            str += formaAceita + ", ";
        }
        str = str.replaceAll(", $", "");
        return str;
    }
}
