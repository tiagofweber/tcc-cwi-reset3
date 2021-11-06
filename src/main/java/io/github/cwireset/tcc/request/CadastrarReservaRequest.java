package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Periodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CadastrarReservaRequest {

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo id do solicitante.")
    private Long idSolicitante;

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo id do anúncio.")
    private Long idAnuncio;

    @Valid
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo periodo.")
    private Periodo periodo;

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo quantidade de pessoas.")
    private Integer quantidadePessoas;

}
