package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.*;
import io.github.cwireset.tcc.exception.reserva.*;
import io.github.cwireset.tcc.repository.ReservaRepository;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.DadosAnuncioResponse;
import io.github.cwireset.tcc.response.DadosSolicitanteResponse;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

//        long quantidadeDiarias = ChronoUnit.DAYS.between(reservaRequest.getPeriodo().getDataHoraInicial(), reservaRequest.getPeriodo().getDataHoraFinal());

        LocalDate dataInicial = reservaRequest.getPeriodo().getDataHoraInicial().toLocalDate();
        LocalDate dataFinal = reservaRequest.getPeriodo().getDataHoraFinal().toLocalDate();

        long quantidadeDiarias = (dataFinal.getDayOfYear() - dataInicial.getDayOfYear());

        BigDecimal valorTotal = anuncio.getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDiarias));

        if (dataInicial.isAfter(dataFinal))
            throw new DataInvalidaException();

        if ((dataFinal.getDayOfYear() - dataInicial.getDayOfYear()) < 1)
            throw new PeriodoInvalidoException();

        if (reservaRequest.getIdSolicitante().equals(anuncio.getAnunciante().getId()))
            throw new ReservaNaoPermitidaException();

        if (anuncio.getImovel().getTipoImovel().equals(TipoImovel.HOTEL) && reservaRequest.getQuantidadePessoas() < 2)
            throw new QuantidadeMinimaDePessoasEmHotelInvalidaException();

        if (anuncio.getImovel().getTipoImovel().equals(TipoImovel.POUSADA) && quantidadeDiarias < 5)
            throw new QuantidadeMinimaDeDiariasEmPousadaInvalidaException();

        List<Reserva> reservaJaExistente = reservaRepository
            .findAllByAnuncioImovelIdEqualsAndPeriodoDataHoraInicialLessThanEqualAndPeriodoDataHoraFinalGreaterThanEqualAndPagamentoStatusNotAndPagamentoStatusNot(
                anuncio.getImovel().getId(),
                reservaRequest.getPeriodo().getDataHoraFinal(),
                reservaRequest.getPeriodo().getDataHoraInicial(),
                StatusPagamento.ESTORNADO,
                StatusPagamento.CANCELADO
            );

        if (!reservaJaExistente.isEmpty())
            throw new PeriodoIndisponivelException();

        LocalDateTime dataHoraInicial = dataInicial.atTime(14, 0, 0);
        LocalDateTime dataHoraFinal = dataFinal.atTime(12, 0, 0);

        reservaRequest.getPeriodo().setDataHoraInicial(dataHoraInicial);
        reservaRequest.getPeriodo().setDataHoraFinal(dataHoraFinal);

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

    public Page<Reserva> listarReservasPorSolicitante(Long idSolicitante, Periodo periodo, Pageable pageable) {
        if (periodo.getDataHoraInicial() == null || periodo.getDataHoraFinal() == null) {
            return reservaRepository.findAllBySolicitanteId(idSolicitante, pageable);
        }
        Page<Reserva> reservas = reservaRepository.findAllBySolicitanteIdAndPeriodoDataHoraInicialAfterAndPeriodoDataHoraFinalBefore(
                idSolicitante,
                periodo.getDataHoraInicial(),
                periodo.getDataHoraFinal(),
                pageable
        );

        return reservas;
    }

    public List<Reserva> listarReservasPorAnunciante(Long idAnunciante) {
        List<Reserva> reservas = reservaRepository.findAllByAnuncioAnuncianteId(idAnunciante);

        return reservas;
    }
}
