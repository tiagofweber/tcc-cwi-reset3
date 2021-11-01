package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.CpfDuplicadoException;
import io.github.cwireset.tcc.exception.CpfNaoEncontradoException;
import io.github.cwireset.tcc.exception.EmailDuplicadoException;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        boolean emailExists = usuarioRepository.existsByEmail(usuario.getEmail());
        boolean cpfExists = usuarioRepository.existsByCpf(usuario.getCpf());

        if (emailExists)
            throw new EmailDuplicadoException(usuario.getEmail());

        if (cpfExists)
            throw new CpfDuplicadoException(usuario.getCpf());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios(Pageable pageable) {
        Page<Usuario> usuariosPaginados = usuarioRepository.findAll(pageable);

        return usuariosPaginados.getContent();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        boolean idExists = usuarioRepository.existsById(id);

        if (!idExists)
            throw new IdNaoEncontradoException(id);

        return usuarioRepository.findById(id).get();
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        boolean cpfExists = usuarioRepository.existsByCpf(cpf);

        if (!cpfExists)
            throw new CpfNaoEncontradoException(cpf);

        return usuarioRepository.findByCpf(cpf);
    }
}
