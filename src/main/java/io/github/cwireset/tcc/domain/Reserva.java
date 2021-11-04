package io.github.cwireset.tcc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "id_anuncio")
    private Anuncio anuncio;

    @Embedded
    private Periodo periodo;

    private Integer quantidadePessoas;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHoraReserva;

    @Embedded
    private Pagamento pagamento;

    public Reserva(Usuario solicitante, Anuncio anuncio, Periodo periodo, Integer quantidadePessoas, LocalDateTime dataHoraReserva) {
        this.solicitante = solicitante;
        this.anuncio = anuncio;
        this.periodo = periodo;
        this.quantidadePessoas = quantidadePessoas;
        this.dataHoraReserva = dataHoraReserva;
    }

}
