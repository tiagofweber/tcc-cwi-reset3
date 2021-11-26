package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AtualizarUsuarioRequest {

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo nome.")
    private String nome;
    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo email.")
    private String email;
    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo senha.")
    private String senha;
    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo data de nascimento.")
    private LocalDate dataNascimento;
    @Valid
    Endereco endereco;

}
