package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.EmailDuplicadoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        boolean emailExists = usuarioRepository.existsByEmail(usuario.getEmail());

        if (emailExists)
            throw new EmailDuplicadoException(usuario.getEmail());

        return usuarioRepository.save(usuario);
    }
}
