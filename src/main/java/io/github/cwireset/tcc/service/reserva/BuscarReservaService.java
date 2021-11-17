package io.github.cwireset.tcc.service.reserva;

import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.exception.reserva.IdReservaNaoEncontradoException;
import io.github.cwireset.tcc.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BuscarReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva buscarReservaPorId(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva).orElse(null);

        if (reserva == null)
            throw new IdReservaNaoEncontradoException(idReserva);
        return reserva;
    }

    public Page<Reserva> listarReservasPorSolicitante(Long idSolicitante, Periodo periodo, Pageable pageable) {
        if (periodo.getDataHoraInicial() == null || periodo.getDataHoraFinal() == null) {
            return reservaRepository.findAllBySolicitanteId(idSolicitante, pageable);
        }

        return reservaRepository.findAllBySolicitanteIdAndPeriodoDataHoraInicialAfterAndPeriodoDataHoraFinalBefore(
                idSolicitante,
                periodo.getDataHoraInicial(),
                periodo.getDataHoraFinal(),
                pageable
        );
    }

    public List<Reserva> listarReservasPorAnunciante(Long idAnunciante, Pageable pageable) {
        return reservaRepository.findAllByAnuncioAnuncianteId(idAnunciante, pageable);
    }

}
