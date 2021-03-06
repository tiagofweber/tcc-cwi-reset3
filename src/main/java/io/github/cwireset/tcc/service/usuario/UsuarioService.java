package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.usuario.CpfDuplicadoException;
import io.github.cwireset.tcc.exception.usuario.EmailDuplicadoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.response.DadosSolicitanteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;

    public void validarCpf(String cpf) {
        if (cpfExists(cpf))
            throw new CpfDuplicadoException(cpf);
    }

    public void validarEmail(String email) {
        if (emailExists(email))
            throw new EmailDuplicadoException(email);
    }

    public Boolean cpfExists(String cpf) {
        return usuarioRepository.existsByCpf(cpf);
    }

    public Boolean emailExists(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public DadosSolicitanteResponse criarSolicitanteResponse(Long idSolicitante) {
        Usuario usuario = buscaUsuarioService.buscarUsuarioPorId(idSolicitante);
        return new DadosSolicitanteResponse(idSolicitante, usuario.getNome());
    }
}
