package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Periodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CadastrarReservaRequest {

    private Long idSolicitante;
    private Long idAnuncio;
    private Periodo periodo;
    private Integer quantidadePessoas;

}
