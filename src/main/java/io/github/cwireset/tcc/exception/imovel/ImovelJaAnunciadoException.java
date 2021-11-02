package io.github.cwireset.tcc.exception.imovel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ImovelJaAnunciadoException extends RuntimeException {
    public ImovelJaAnunciadoException(Long idImovel) {
        super(String.format("Já existe um recurso do tipo Anuncio com IdImovel com o valor '%d'.", idImovel));
    }
}
