package io.github.cwireset.tcc.service.imovel;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.imovel.ExcluirImovelAnunciadoException;
import io.github.cwireset.tcc.repository.ImovelRepository;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.service.anuncio.AnuncioService;
import io.github.cwireset.tcc.service.usuario.BuscaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private BuscaImovelService buscaImovelService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;
    @Autowired
    private AnuncioService anuncioService;

    public Imovel cadastrarImovel(CadastrarImovelRequest imovelRequest) {
        Usuario proprietario = buscaUsuarioService.buscarUsuarioPorId(imovelRequest.getIdProprietario());

        Imovel imovel = new Imovel(
                imovelRequest.getIdentificacao(),
                imovelRequest.getTipoImovel(),
                imovelRequest.getEndereco(),
                proprietario,
                imovelRequest.getCaracteristicas(),
                true
        );

        return imovelRepository.save(imovel);
    }

    public void removerImovel(Long id) {
        Imovel imovel = buscaImovelService.buscarImovelPorId(id);
        Boolean anuncioExiste = anuncioService.existeAnuncioDeImovel(imovel);

        if (anuncioExiste)
            throw new ExcluirImovelAnunciadoException();

        imovel.setAtivo(false);

        imovelRepository.save(imovel);
    }
}
