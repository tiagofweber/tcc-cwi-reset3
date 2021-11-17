package io.github.cwireset.tcc.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo nome.")
    private String nome;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo email.")
    private String email;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo senha.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "Campo obrigatório não informado. Por favor, informe o campo CPF.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ser informado no formato 99999999999.")
    private String cpf;

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo data de nascimento.")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    @Valid
    private Endereco endereco;

    private String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && nome.equals(usuario.nome) && email.equals(usuario.email) && senha.equals(usuario.senha) && cpf.equals(usuario.cpf) && dataNascimento.equals(usuario.dataNascimento) && Objects.equals(endereco, usuario.endereco) && Objects.equals(avatar, usuario.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, cpf, dataNascimento, endereco, avatar);
    }
}
