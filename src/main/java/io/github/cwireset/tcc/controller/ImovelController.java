package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Imovel;
import io.github.cwireset.tcc.request.CadastrarImovelRequest;
import io.github.cwireset.tcc.service.imovel.BuscaImovelService;
import io.github.cwireset.tcc.service.imovel.CadastroImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/imoveis")
public class ImovelController {

    @Autowired
    private CadastroImovelService cadastroImovelService;
    @Autowired
    private BuscaImovelService buscaImovelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imovel cadastrarImovel(@RequestBody @Valid CadastrarImovelRequest imovelRequest) {
        return cadastroImovelService.cadastrarImovel(imovelRequest);
    }

    @GetMapping
    public Page<Imovel> listarImoveis(
            @PageableDefault(sort = "identificacao") @ApiIgnore Pageable pageable
    ) {
        return buscaImovelService.listarImoveis(pageable);
    }

    @GetMapping(path = "/proprietarios/{idProprietario}")
    public Page<Imovel> listarImoveisPorProprietario(
            @PathVariable Long idProprietario,
            @PageableDefault(sort = "identificacao") @ApiIgnore Pageable pageable
    ) {
        return buscaImovelService.listarImoveisPorProprietario(idProprietario, pageable);
    }

    @GetMapping(path = "/{idImovel}")
    public Imovel buscarImovelPorId(@PathVariable("idImovel") Long id) {
        return buscaImovelService.buscarImovelPorId(id);
    }

    @DeleteMapping(path = "/{idImovel}")
    public void removerImovel(@PathVariable("idImovel") Long id) {
        cadastroImovelService.removerImovel(id);
    }
}
