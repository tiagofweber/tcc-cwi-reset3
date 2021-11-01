package io.github.cwireset.tcc.request;

import io.github.cwireset.tcc.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AtualizarUsuarioRequest {

    private String nome;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    Endereco endereco;

}
