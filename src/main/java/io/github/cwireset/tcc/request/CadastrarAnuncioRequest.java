package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CadastrarAnuncioRequest {

    private Long idImovel;
    private Long idAnunciante;
    private TipoAnuncio tipoAnuncio;
    private BigDecimal valorDiaria;
    private List<FormaPagamento> formasAceitas;
    private String descricao;

}
