package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest usuarioRequest) {
        Usuario usuario = buscarUsuarioService.buscarUsuarioPorId(id);

        if (!usuario.getEmail().equals(usuarioRequest.getEmail()))
            cadastrarUsuarioService.validarEmail(usuarioRequest.getEmail());

        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setDataNascimento(usuarioRequest.getDataNascimento());
        usuario.getEndereco().setCep(usuarioRequest.getEndereco().getCep());
        usuario.getEndereco().setLogradouro(usuarioRequest.getEndereco().getLogradouro());
        usuario.getEndereco().setNumero(usuarioRequest.getEndereco().getNumero());
        usuario.getEndereco().setComplemento(usuarioRequest.getEndereco().getComplemento());
        usuario.getEndereco().setBairro(usuarioRequest.getEndereco().getBairro());
        usuario.getEndereco().setCidade(usuarioRequest.getEndereco().getCidade());
        usuario.getEndereco().setEstado(usuarioRequest.getEndereco().getEstado());

        return usuarioRepository.save(usuario);
    }

}
