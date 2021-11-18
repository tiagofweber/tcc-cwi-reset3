package io.github.cwireset.tcc.service.imovel;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.exception.imovel.IdImovelNaoEncontradoException;
import io.github.cwireset.tcc.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

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
}
