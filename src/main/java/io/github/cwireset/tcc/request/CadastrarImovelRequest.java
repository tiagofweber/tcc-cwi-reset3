package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.CaracteristicaImovel;
import io.github.cwireset.tcc.domain.Endereco;
import io.github.cwireset.tcc.domain.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CadastrarImovelRequest {

    private TipoImovel tipoImovel;
    private Endereco endereco;
    private String identificacao;
    private Long idProprietario;
    private List<CaracteristicaImovel> caracteristicas;

}
