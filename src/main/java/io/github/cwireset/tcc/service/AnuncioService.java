package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.anuncio.IdAnuncioNaoEncontradoException;
import io.github.cwireset.tcc.exception.imovel.ImovelJaAnunciadoException;
import io.github.cwireset.tcc.repository.AnuncioRepository;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
                anuncioRequest.getDescricao(),
                true
        );

        return anuncioRepository.save(anuncio);
    }

    public List<Anuncio> listarAnuncios(Pageable pageable) {
        Page<Anuncio> anunciosPaginados = anuncioRepository.findAllByAtivoTrue(pageable);

        return anunciosPaginados.getContent();
    }

    public List<Anuncio> listarAnunciosPorAnunciante(Long idAnunciante, Pageable pageable) {
        Page<Anuncio> anunciosPaginados = anuncioRepository.findAllByAnuncianteIdAndAtivoTrue(idAnunciante, pageable);

        return anunciosPaginados.getContent();
    }

    public void removerAnuncio(Long id) {
        boolean anuncioExists = anuncioRepository.existsById(id);

        if (!anuncioExists)
            throw new IdAnuncioNaoEncontradoException(id);

        Anuncio anuncio = anuncioRepository.findById(id).get();

        anuncio.setAtivo(false);
        anuncioRepository.save(anuncio);
    }

    public Boolean existeAnuncioDeImovel(Imovel imovel) {
        return anuncioRepository.existsByImovelId(imovel.getId());
    }
}
