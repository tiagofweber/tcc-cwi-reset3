package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.imovel.ImovelJaAnunciadoException;
import io.github.cwireset.tcc.repository.AnuncioRepository;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ImovelService imovelService;

    public Anuncio cadastrarAnuncio(CadastrarAnuncioRequest anuncioRequest) {
        boolean anuncioImovelExists = anuncioRepository.existsByImovelId(anuncioRequest.getIdImovel());

        if (anuncioImovelExists)
            throw new ImovelJaAnunciadoException(anuncioRequest.getIdImovel());

        Imovel imovel = imovelService.buscarImovelPorId(anuncioRequest.getIdImovel());
        Usuario anunciante = usuarioService.buscarUsuarioPorId(anuncioRequest.getIdAnunciante());

        Anuncio anuncio = new Anuncio(
                anuncioRequest.getTipoAnuncio(),
                imovel,
                anunciante,
                anuncioRequest.getValorDiaria(),
                anuncioRequest.getFormasAceitas(),
                anuncioRequest.getDescricao()
        );

        return anuncioRepository.save(anuncio);
    }
}
