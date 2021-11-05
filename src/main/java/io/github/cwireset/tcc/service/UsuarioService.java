package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.usuario.CpfDuplicadoException;
import io.github.cwireset.tcc.exception.usuario.CpfNaoEncontradoException;
import io.github.cwireset.tcc.exception.usuario.EmailDuplicadoException;
import io.github.cwireset.tcc.exception.usuario.IdUsuarioNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import io.github.cwireset.tcc.response.DadosSolicitanteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        boolean idExists = usuarioRepository.existsById(id);

        if (!idExists)
            throw new IdUsuarioNaoEncontradoException(id);

        return usuarioRepository.findById(id).get();
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {
        boolean cpfExists = usuarioRepository.existsByCpf(cpf);

        if (!cpfExists)
            throw new CpfNaoEncontradoException(cpf);

        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest usuarioRequest) {
        Usuario usuario = buscarUsuarioPorId(id);

        if (!usuario.getEmail().equals(usuarioRequest.getEmail()))
            validarEmail(usuarioRequest.getEmail());

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

    public DadosSolicitanteResponse criarSolicitanteResponse(Long idSolicitante) {
        Usuario usuario = buscarUsuarioPorId(idSolicitante);
        return new DadosSolicitanteResponse(idSolicitante, usuario.getNome());
    }
}
