package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Anuncio;
import io.github.cwireset.tcc.request.CadastrarAnuncioRequest;
import io.github.cwireset.tcc.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio cadastrarAnuncio(@RequestBody CadastrarAnuncioRequest anuncioRequest) {
        return anuncioService.cadastrarAnuncio(anuncioRequest);
    }
}
