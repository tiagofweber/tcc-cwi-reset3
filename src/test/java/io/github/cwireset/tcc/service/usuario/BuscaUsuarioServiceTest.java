package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.exception.usuario.CpfNaoEncontradoException;
import io.github.cwireset.tcc.exception.usuario.IdUsuarioNaoEncontradoException;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class BuscaUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private BuscaUsuarioService buscarUsuario;

    @Test
    public void listarUsuariosComSucesso() {
        Page<Usuario> usuarios = Page.empty();
        Pageable pageable = mock(Pageable.class);

        when(buscarUsuario.listarUsuarios(any())).thenReturn(usuarios);

        buscarUsuario.listarUsuarios(pageable);

        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    public void buscarUsuarioPorIdComUsuarioExistente() {
        // Arrange
        when(repository.existsById(any())).thenReturn(true);

        // Action
        buscarUsuario.buscarUsuarioPorId(any());

        // Assert
        verify(repository, times(1)).findByIdEquals(any());
    }

    @Test
    public void buscarUsuarioPorIdComUsuarioNaoExistente() {
        // Arrange
        String expected = "Nenhum(a) Usuario com Id com o valor '999' foi encontrado.";
        when(repository.existsById(any())).thenReturn(false);

        // Action
        Throwable e = assertThrows(
                IdUsuarioNaoEncontradoException.class,
                () -> buscarUsuario.buscarUsuarioPorId(999L)
        );
        assertEquals(expected, e.getMessage());
    }

    @Test
    public void buscarUsuarioPorCpfComUsuarioExistente() {
        // Arrange
        when(repository.existsByCpf(any())).thenReturn(true);

        // Action
        buscarUsuario.buscarUsuarioPorCpf(any());

        // Assert
        verify(repository, times(1)).findByCpf(any());
    }

    @Test
    public void buscarUsuarioPorCpfComUsuarioNaoExistente() {
        // Arrange
        String expected = "Nenhum(a) Usuario com CPF com o valor '01245487848' foi encontrado.";
        when(repository.existsByCpf(any())).thenReturn(false);

        // Action
        Throwable e = assertThrows(
                CpfNaoEncontradoException.class,
                () -> buscarUsuario.buscarUsuarioPorCpf("01245487848")
        );
        assertEquals(expected, e.getMessage());
    }

}
