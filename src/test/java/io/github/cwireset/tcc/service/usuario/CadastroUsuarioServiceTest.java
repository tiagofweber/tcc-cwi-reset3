package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.UsuarioAvatarRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastroUsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioAvatarService usuarioAvatarService;

    @InjectMocks
    private CadastroUsuarioService cadastroUsuarioService;

    @Captor
    private ArgumentCaptor<Usuario> usuarioArgumentCaptor;

    private Usuario buildUsuarioRequest() {
        return Usuario.builder()
                .nome("Jane Doe")
                .email("janedoe@email.com")
                .senha("qwerty")
                .cpf("12312312312")
                .dataNascimento(LocalDate.of(1990, 12, 10))
                .build();
    }

    @Test
    public void criarUsuarioComSucesso() {
        // Arrange
        String linkAvatar = "www.linkavatar.com";
        Usuario usuarioRequest = buildUsuarioRequest();
        Usuario expected = Usuario.builder()
                .nome(usuarioRequest.getNome())
                .email(usuarioRequest.getEmail())
                .senha(usuarioRequest.getSenha())
                .cpf(usuarioRequest.getCpf())
                .dataNascimento(usuarioRequest.getDataNascimento())
                .avatar(linkAvatar)
                .build();
        UsuarioAvatarRequest usuarioAvatar = UsuarioAvatarRequest.builder()
                .link(linkAvatar)
                .build();
        when(usuarioAvatarService.buscarAvatar()).thenReturn(usuarioAvatar);
        when(usuarioRepository.save(usuarioArgumentCaptor.capture())).thenReturn(expected);

        // Action
        cadastroUsuarioService.criarUsuario(usuarioRequest);

        // Assert
        verify(usuarioService, times(1)).validarEmail(anyString());
        verify(usuarioService, times(1)).validarCpf(anyString());
        verify(usuarioRepository, times(1)).save(any());

        assertEquals(expected, usuarioArgumentCaptor.getValue());
    }

}
