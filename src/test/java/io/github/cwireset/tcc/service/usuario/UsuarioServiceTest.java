package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.exception.usuario.CpfDuplicadoException;
import io.github.cwireset.tcc.exception.usuario.EmailDuplicadoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BuscaUsuarioService buscaUsuarioService;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void verificarEmailComEmailJaExistente() {
        // Arrange
        String email = "teste@email.com";
        when(usuarioService.emailExists(any())).thenReturn(true);

        // Assert
        Throwable e =  assertThrows(EmailDuplicadoException.class, () -> usuarioService.validarEmail(email));
        assertEquals(
                "Já existe um recurso do tipo Usuario com E-Mail com o valor 'teste@email.com'.",
                e.getMessage()
        );
    }

    @Test
    public void verificarCpfComCpfJaExistente() {
        // Arrange
        String cpf = "99999999999";
        when(usuarioService.cpfExists(cpf)).thenReturn(true);

        // Assert
        Throwable e =  assertThrows(CpfDuplicadoException.class, () -> usuarioService.validarCpf(cpf));
        assertEquals(
                "Já existe um recurso do tipo Usuario com CPF com o valor '99999999999'.",
                e.getMessage()
        );
    }
}
