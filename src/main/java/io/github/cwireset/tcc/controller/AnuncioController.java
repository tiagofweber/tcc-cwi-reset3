package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio cadastrarAnuncio(@RequestBody @Valid CadastrarAnuncioRequest anuncioRequest) {
        return anuncioService.cadastrarAnuncio(anuncioRequest);
    }

    @GetMapping
    public List<Anuncio> listarAnuncios(@PageableDefault(sort = "valorDiaria") @ApiIgnore Pageable pageable) {
        return anuncioService.listarAnuncios(pageable);
    }

    @GetMapping(path = "/anunciantes/{idAnunciante}")
    public List<Anuncio> listarAnunciosPorAnunciante(
            @PathVariable("idAnunciante") Long idAnunciante,
            @PageableDefault(sort = "valorDiaria") @ApiIgnore Pageable pageable
    ) {
        return anuncioService.listarAnunciosPorAnunciante(idAnunciante, pageable);
    }
}
