package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CadastrarAnuncioRequest {

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo id do imóvel.")
    private Long idImovel;
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo id do anunciante.")
    private Long idAnunciante;
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo tipo do anúncio.")
    private TipoAnuncio tipoAnuncio;
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo valor da diária.")
    private BigDecimal valorDiaria;
    @NotEmpty(message = "Campo obrigatório não informado. Por favor, informe o campo formas de pagamento aceitas.")
    private List<FormaPagamento> formasAceitas;
    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo descrição.")
    private String descricao;

}
