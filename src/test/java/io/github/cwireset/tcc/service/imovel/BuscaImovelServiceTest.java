package io.github.cwireset.tcc.service.imovel;

import io.github.cwireset.tcc.exception.imovel.IdImovelNaoEncontradoException;
import io.github.cwireset.tcc.repository.ImovelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscaImovelServiceTest {

    @Mock
    private ImovelRepository imovelRepository;

    @InjectMocks
    private BuscaImovelService buscaImovelService;

    @Test
    public void listarImoveis() {
        buscaImovelService.listarImoveis(any());

        verify(imovelRepository, times(1)).findAllByAtivoTrue(any());
    }

    @Test
    public void listarImoveisPorProprietario() {
        buscaImovelService.listarImoveisPorProprietario(any(), any());

        verify(imovelRepository, times(1)).findAllByProprietarioIdAndAtivoTrue(any(), any());
    }

    @Test
    public void buscarImovelPorIdComImovelExistente() {
        when(imovelRepository.existsByIdAndAtivoTrue(any())).thenReturn(true);

        buscaImovelService.buscarImovelPorId(any());

        verify(imovelRepository, times(1)).findByIdEqualsAndAtivoTrue(any());
    }

    @Test
    public void buscarImovelPorIdComImovelNaoExistente() {
        String expected = "Nenhum(a) Imovel com Id com o valor '999' foi encontrado.";

        when(imovelRepository.existsByIdAndAtivoTrue(any())).thenReturn(false);

        Throwable e = assertThrows(IdImovelNaoEncontradoException.class, () -> buscaImovelService.buscarImovelPorId(999L));
        assertEquals(expected, e.getMessage());
    }

}
