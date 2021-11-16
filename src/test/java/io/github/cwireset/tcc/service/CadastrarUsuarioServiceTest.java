package io.github.cwireset.tcc.service;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.UsuarioAvatarRequest;
import io.github.cwireset.tcc.service.usuario.CadastrarUsuarioService;
import io.github.cwireset.tcc.service.usuario.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastrarUsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioAvatarService usuarioAvatarService;

    @InjectMocks
    private CadastrarUsuarioService cadastrarUsuarioService;

    private final Usuario usuario = Usuario.builder()
            .id(1L)
            .nome("Jane Doe")
            .email("janedoe@email.com")
            .senha("qwerty")
            .cpf("99999999999")
            .dataNascimento(LocalDate.of(1990, 12, 10))
            .build();

    @Test
    public void cadastrarUsuarioComSucesso() {
        // Arrange
        String linkAvatar = "www.linkavatar.com";
        UsuarioAvatarRequest usuarioAvatar = UsuarioAvatarRequest.builder()
                .link(linkAvatar)
                .build();
        when(usuarioAvatarService.buscarAvatar()).thenReturn(usuarioAvatar);

        // Action
        cadastrarUsuarioService.criarUsuario(usuario);

        // Assert
        verify(usuarioService).validarEmail(anyString());
        verify(usuarioService).validarCpf(anyString());
        verify(usuarioRepository).save(any(Usuario.class));
    }
}
