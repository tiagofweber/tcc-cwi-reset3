package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.usuario.CpfDuplicadoException;
import io.github.cwireset.tcc.exception.usuario.EmailDuplicadoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.UsuarioAvatarRequest;
import io.github.cwireset.tcc.response.DadosSolicitanteResponse;
import io.github.cwireset.tcc.service.UsuarioAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private UsuarioAvatarService avatarService;

    public Usuario criarUsuario(Usuario usuario) {
        boolean cpfExists = usuarioRepository.existsByCpf(usuario.getCpf());

        validarEmail(usuario.getEmail());

        if (cpfExists)
            throw new CpfDuplicadoException(usuario.getCpf());

        UsuarioAvatarRequest usuarioAvatar = avatarService.buscarAvatar();

        usuario.setAvatar(usuarioAvatar.getLink());

        return usuarioRepository.save(usuario);
    }

    public void validarEmail(String email) {
        boolean emailExists = usuarioRepository.existsByEmail(email);

        if (emailExists)
            throw new EmailDuplicadoException(email);
    }

    public DadosSolicitanteResponse criarSolicitanteResponse(Long idSolicitante) {
        Usuario usuario = buscarUsuarioService.buscarUsuarioPorId(idSolicitante);
        return new DadosSolicitanteResponse(idSolicitante, usuario.getNome());
    }
}
