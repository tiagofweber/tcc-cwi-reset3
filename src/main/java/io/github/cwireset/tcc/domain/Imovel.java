package io.github.cwireset.tcc.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificacao;

    @Enumerated(EnumType.STRING)
    private TipoImovel tipoImovel;

    @NotNull(message = "Campo obrigatório não informado. Por favor, informe o campo endereço.")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_proprietario")
    private Usuario proprietario;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_imovel")
    private List<CaracteristicaImovel> caracteristicas;

    public Imovel(String identificacao, TipoImovel tipoImovel, Endereco endereco, Usuario proprietario, List<CaracteristicaImovel> caracteristicas) {
        this.identificacao = identificacao;
        this.tipoImovel = tipoImovel;
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.caracteristicas = caracteristicas;
    }

}
