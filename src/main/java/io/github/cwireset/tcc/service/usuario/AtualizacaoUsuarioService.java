package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizacaoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest usuarioRequest) {
        Usuario usuario = buscaUsuarioService.buscarUsuarioPorId(id);

        if (!usuario.getEmail().equals(usuarioRequest.getEmail()))
            usuarioService.validarEmail(usuarioRequest.getEmail());

        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDataNascimento(usuarioRequest.getDataNascimento());

        if (usuario.getEndereco() != null) {
            usuario.getEndereco().setCep(usuarioRequest.getEndereco().getCep());
            usuario.getEndereco().setLogradouro(usuarioRequest.getEndereco().getLogradouro());
            usuario.getEndereco().setNumero(usuarioRequest.getEndereco().getNumero());
            usuario.getEndereco().setComplemento(usuarioRequest.getEndereco().getComplemento());
            usuario.getEndereco().setBairro(usuarioRequest.getEndereco().getBairro());
            usuario.getEndereco().setCidade(usuarioRequest.getEndereco().getCidade());
            usuario.getEndereco().setEstado(usuarioRequest.getEndereco().getEstado());
        }

        return usuarioRepository.save(usuario);
    }

}
