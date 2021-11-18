package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.UsuarioAvatarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioAvatarService avatarService;

    public Usuario criarUsuario(Usuario usuario) {
        usuarioService.validarEmail(usuario.getEmail());
        usuarioService.validarCpf(usuario.getCpf());

        UsuarioAvatarRequest usuarioAvatar = avatarService.buscarAvatar();

        usuario.setAvatar(usuarioAvatar.getLink());

        return usuarioRepository.save(usuario);
    }

}
