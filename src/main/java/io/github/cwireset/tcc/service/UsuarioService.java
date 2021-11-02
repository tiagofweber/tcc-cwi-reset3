package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.CpfDuplicadoException;
import io.github.cwireset.tcc.exception.CpfNaoEncontradoException;
import io.github.cwireset.tcc.exception.EmailDuplicadoException;
import io.github.cwireset.tcc.exception.IdNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
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
        boolean cpfExists = usuarioRepository.existsByCpf(usuario.getCpf());

        validarEmail(usuario.getEmail());

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

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest usuarioRequest) {
        validarEmail(usuarioRequest.getEmail());

        Usuario usuario = buscarUsuarioPorId(id);

        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDataNascimento(usuarioRequest.getDataNascimento());
        usuario.setEndereco(usuarioRequest.getEndereco());

        return usuarioRepository.save(usuario);
    }

    private void validarEmail(String email) {
        boolean emailExists = usuarioRepository.existsByEmail(email);

        if (emailExists)
            throw new EmailDuplicadoException(email);
    }
}
