package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.imovel.ExcluirImovelAnunciadoException;
import io.github.cwireset.tcc.exception.imovel.IdImovelNaoEncontradoException;
import io.github.cwireset.tcc.repository.ImovelRepository;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.service.usuario.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private AnuncioService anuncioService;

    public Imovel cadastrarImovel(CadastrarImovelRequest imovelRequest) {
        Usuario proprietario = buscarUsuarioService.buscarUsuarioPorId(imovelRequest.getIdProprietario());

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

    public Page<Imovel> listarImoveis(Pageable pageable) {
        return imovelRepository.findAllByAtivoTrue(pageable);
    }

    public Page<Imovel> listarImoveisPorProprietario(Long idProprietario, Pageable pageable) {
        return imovelRepository.findAllByProprietarioIdAndAtivoTrue(idProprietario, pageable);
    }

    public Imovel buscarImovelPorId(Long id) {
        boolean idExists = imovelRepository.existsByIdAndAtivoTrue(id);

        if (!idExists)
            throw new IdImovelNaoEncontradoException(id);

        return imovelRepository.findByIdEqualsAndAtivoTrue(id);
    }

    public void removerImovel(Long id) {
        Imovel imovel = buscarImovelPorId(id);
        Boolean anuncioExiste = anuncioService.existeAnuncioDeImovel(imovel);

        if (anuncioExiste)
            throw new ExcluirImovelAnunciadoException();

        imovel.setAtivo(false);

        imovelRepository.save(imovel);
    }
}
