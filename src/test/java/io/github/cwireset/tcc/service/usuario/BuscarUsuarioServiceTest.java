package io.github.cwireset.tcc.service.usuario;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class BuscarUsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private BuscarUsuarioService buscarUsuario;

    @Test
    public void listarUsuariosComSucesso() {
        Page<Usuario> usuarios = Page.empty();
        Pageable pageable = mock(Pageable.class);

        when(buscarUsuario.listarUsuarios(any())).thenReturn(usuarios);

        buscarUsuario.listarUsuarios(pageable);

        verify(repository).findAll(any(Pageable.class));
    }

}
