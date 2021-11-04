package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.*;
import io.github.cwireset.tcc.repository.ReservaRepository;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.DadosAnuncioResponse;
import io.github.cwireset.tcc.response.DadosSolicitanteResponse;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnuncioService anuncioService;

    public InformacaoReservaResponse realizarReserva(CadastrarReservaRequest reservaRequest) {
        Usuario solicitante = usuarioService.buscarUsuarioPorId(reservaRequest.getIdSolicitante());
        Anuncio anuncio = anuncioService.buscarAnuncioPorId(reservaRequest.getIdAnuncio());

        long quantidadeDiarias = ChronoUnit.DAYS.between(reservaRequest.getPeriodo().getDataHoraInicial(), reservaRequest.getPeriodo().getDataHoraFinal());
        BigDecimal valorTotal = anuncio.getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDiarias));

        Pagamento pagamento = new Pagamento(valorTotal, StatusPagamento.PENDENTE);

        Reserva reserva = new Reserva(
                solicitante,
                anuncio,
                reservaRequest.getPeriodo(),
                reservaRequest.getQuantidadePessoas(),
                LocalDateTime.now(),
                pagamento
        );

        Reserva reservaSalva = reservaRepository.save(reserva);

        DadosSolicitanteResponse solicitanteResponse = usuarioService.criarSolicitanteResponse(reservaRequest.getIdSolicitante());
        DadosAnuncioResponse anuncioResponse = anuncioService.criarAnuncioResponse(reservaRequest.getIdAnuncio());

        return new InformacaoReservaResponse(
                reservaSalva.getId(),
                solicitanteResponse,
                reserva.getQuantidadePessoas(),
                anuncioResponse,
                reservaSalva.getPeriodo(),
                reservaSalva.getPagamento()
        );
    }
}