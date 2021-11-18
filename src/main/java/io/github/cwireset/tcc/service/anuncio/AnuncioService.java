package io.github.cwireset.tcc.service.anuncio;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.anuncio.IdAnuncioNaoEncontradoException;
import io.github.cwireset.tcc.exception.imovel.ImovelJaAnunciadoException;
import io.github.cwireset.tcc.repository.AnuncioRepository;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.response.DadosAnuncioResponse;
import io.github.cwireset.tcc.service.imovel.BuscaImovelService;
import io.github.cwireset.tcc.service.imovel.CadastroImovelService;
import io.github.cwireset.tcc.service.usuario.BuscaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;
    @Autowired
    private CadastroImovelService cadastroImovelService;
    @Autowired
    private BuscaImovelService buscaImovelService;

    public Anuncio cadastrarAnuncio(CadastrarAnuncioRequest anuncioRequest) {
        boolean anuncioImovelExists = anuncioRepository.existsByImovelId(anuncioRequest.getIdImovel());

        if (anuncioImovelExists)
            throw new ImovelJaAnunciadoException(anuncioRequest.getIdImovel());

        Imovel imovel = buscaImovelService.buscarImovelPorId(anuncioRequest.getIdImovel());
        Usuario anunciante = buscaUsuarioService.buscarUsuarioPorId(anuncioRequest.getIdAnunciante());

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

    public Page<Anuncio> listarAnuncios(Pageable pageable) {
        return anuncioRepository.findAllByAtivoTrue(pageable);
    }

    public Page<Anuncio> listarAnunciosPorAnunciante(Long idAnunciante, Pageable pageable) {
        return anuncioRepository.findAllByAnuncianteIdAndAtivoTrue(idAnunciante, pageable);
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
        return anuncioRepository.existsByImovelIdAndAtivoTrue(imovel.getId());
    }

    public Anuncio buscarAnuncioPorId(Long idAnuncio) {
        boolean anuncioExists = anuncioRepository.existsById(idAnuncio);

        if (!anuncioExists)
            throw new IdAnuncioNaoEncontradoException(idAnuncio);

        return anuncioRepository.findById(idAnuncio).get();
    }

    public DadosAnuncioResponse criarAnuncioResponse(Long idAnuncio) {
        Anuncio anuncio = buscarAnuncioPorId(idAnuncio);
        Imovel imovel = buscaImovelService.buscarImovelPorId(anuncio.getImovel().getId());
        Usuario anunciante = buscaUsuarioService.buscarUsuarioPorId(anuncio.getAnunciante().getId());

        return new DadosAnuncioResponse(
                idAnuncio,
                imovel,
                anunciante,
                anuncio.getFormasAceitas(),
                anuncio.getDescricao()
        );
    }
}
