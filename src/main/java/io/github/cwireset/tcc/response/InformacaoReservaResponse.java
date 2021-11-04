package io.github.cwireset.tcc.response;

import io.github.cwireset.tcc.domain.Pagamento;
import io.github.cwireset.tcc.domain.Periodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InformacaoReservaResponse {

    private Long idReserva;
    private DadosSolicitanteResponse solicitante;
    private Integer quantidadePessoas;
    private DadosAnuncioResponse anuncio;
    private Periodo periodo;
    private Pagamento pagamento;
}
