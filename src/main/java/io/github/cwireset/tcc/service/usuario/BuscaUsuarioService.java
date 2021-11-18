package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.usuario.CpfNaoEncontradoException;
import io.github.cwireset.tcc.exception.usuario.IdUsuarioNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscaUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        boolean idExists = usuarioRepository.existsById(id);

        if (!idExists)
            throw new IdUsuarioNaoEncontradoException(id);

        return usuarioRepository.findByIdEquals(id);
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        boolean cpfExists = usuarioRepository.existsByCpf(cpf);

        if (!cpfExists)
            throw new CpfNaoEncontradoException(cpf);

        return usuarioRepository.findByCpf(cpf);
    }
}
