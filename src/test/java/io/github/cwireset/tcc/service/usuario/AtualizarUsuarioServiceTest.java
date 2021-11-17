package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Endereco;
import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtualizarUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @InjectMocks
    private AtualizarUsuarioService atualizarUsuarioService;

    @Captor
    ArgumentCaptor<Usuario> usuarioArgumentCaptor;

    @Test
    public void atualizarUsuarioComSucesso() {
        // Arrange
        Endereco endereco = new Endereco(
                1L,
                "123123-12",
                "Avenida das Hortências",
                "489",
                "",
                "Bom Fim"
                , "Camaquã",
                "RS"
        );
        Usuario usuarioCadastrado = new Usuario(
                1L,
                "Jane Doe",
                "janedoe@email.com",
                "qwerty",
                "12312312312",
                LocalDate.of(1990, 12, 10),
                endereco,
                "string-avatar.com"
        );

        AtualizarUsuarioRequest atualizarUsuarioRequest = new AtualizarUsuarioRequest(
                "Janete Dias",
                "janetedias@email.com",
                "ytrewq",
                LocalDate.of(1987, 3, 27),
                endereco
        );

        Usuario usuarioExpected = new Usuario(
                1L,
                "Janete Dias",
                "janetedias@email.com",
                "ytrewq",
                "12312312312",
                LocalDate.of(1987, 3, 27),
                endereco,
                "string-avatar.com"
        );

        when(buscarUsuarioService.buscarUsuarioPorId(any())).thenReturn(usuarioCadastrado);

        // Action
        atualizarUsuarioService.atualizarUsuario(1L, atualizarUsuarioRequest);

        // Assert
        verify(repository, times(1)).save(usuarioArgumentCaptor.capture());
        assertEquals(usuarioExpected, usuarioArgumentCaptor.getValue());
    }

}
