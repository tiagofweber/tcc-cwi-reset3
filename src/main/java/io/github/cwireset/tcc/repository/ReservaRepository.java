package io.github.cwireset.tcc.repository;

import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.domain.StatusPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findAllByAnuncioImovelIdEqualsAndPeriodoDataHoraInicialLessThanEqualAndPeriodoDataHoraFinalGreaterThanEqualAndPagamentoStatusNotAndPagamentoStatusNot(
            Long id,
            LocalDateTime dataHoraFinal,
            LocalDateTime dataHoraInicial,
            StatusPagamento statusPagamento1,
            StatusPagamento statusPagamento2
    );
    Page<Reserva> findAllBySolicitanteIdAndPeriodoDataHoraInicialAfterAndPeriodoDataHoraFinalBefore(
            Long idSolicitante,
            LocalDateTime dataHoraInicial,
            LocalDateTime dataHoraFinal,
            Pageable pageable
    );
    Page<Reserva> findAllBySolicitanteId(Long idSolicitante, Pageable pageable);
    Page<Reserva> findAllByAnuncioAnuncianteId(Long idAnunciante, Pageable pageable);
}
