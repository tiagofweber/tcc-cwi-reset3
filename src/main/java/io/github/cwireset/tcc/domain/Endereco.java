package io.github.cwireset.tcc.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo cep.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve ser informado no formato 99999-999.")
    private String cep;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo logradouro.")
    private String logradouro;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo número.")
    private String numero;

    private String complemento;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo bairro.")
    private String bairro;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo cidade.")
    private String cidade;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo estado.")
    private String estado;

}
