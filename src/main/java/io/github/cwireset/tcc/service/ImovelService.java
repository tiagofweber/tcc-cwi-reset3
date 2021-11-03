package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.imovel.ExcluirImovelAnunciadoException;
import io.github.cwireset.tcc.exception.imovel.IdImovelNaoEncontradoException;
import io.github.cwireset.tcc.repository.ImovelRepository;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AnuncioService anuncioService;

    public Imovel cadastrarImovel(CadastrarImovelRequest imovelRequest) {
        Usuario proprietario = usuarioService.buscarUsuarioPorId(imovelRequest.getIdProprietario());

        Imovel imovel = new Imovel(
                imovelRequest.getIdentificacao(),
                imovelRequest.getTipoImovel(),
                imovelRequest.getEndereco(),
                proprietario,
                imovelRequest.getCaracteristicas()
        );

        return imovelRepository.save(imovel);
    }

    public List<Imovel> listarImoveis(Pageable pageable) {
        Page<Imovel> imoveisPaginados = imovelRepository.findAll(pageable);

        return imoveisPaginados.getContent();
    }

    public List<Imovel> listarImoveisPorProprietario(Long idProprietario, Pageable pageable) {
        Page<Imovel> imoveisPaginados = imovelRepository.findAllByProprietarioId(idProprietario, pageable);

        return imoveisPaginados.getContent();
    }

    public Imovel buscarImovelPorId(Long id) {
        boolean idExists = imovelRepository.existsById(id);

        if (!idExists)
            throw new IdImovelNaoEncontradoException(id);

        return imovelRepository.findById(id).get();
    }

    public void removerImovel(Long id) {
        Imovel imovel = buscarImovelPorId(id);
        Boolean anuncioExiste = anuncioService.existeAnuncioDeImovel(imovel);

        if (anuncioExiste)
            throw new ExcluirImovelAnunciadoException();

        imovelRepository.delete(imovel);
    }
}
