package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.CaracteristicaImovel;
import io.github.cwireset.tcc.domain.Endereco;
import io.github.cwireset.tcc.domain.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CadastrarImovelRequest {

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo tipo imóvel.")
    private TipoImovel tipoImovel;
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo endereço.")
    private Endereco endereco;
    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo identificação.")
    private String identificacao;
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo id proprietário.")
    private Long idProprietario;
    private List<CaracteristicaImovel> caracteristicas;

}
