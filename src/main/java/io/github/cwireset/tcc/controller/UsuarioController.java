package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import io.github.cwireset.tcc.service.usuario.AtualizacaoUsuarioService;
import io.github.cwireset.tcc.service.usuario.BuscaUsuarioService;
import io.github.cwireset.tcc.service.usuario.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;
    @Autowired
    private AtualizacaoUsuarioService atualizacaoUsuarioService;
    @Autowired
    private BuscaUsuarioService buscaUsuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario) {
        return cadastroUsuarioService.criarUsuario(usuario);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(@PageableDefault(sort = "nome") @ApiIgnore Pageable pageable) {
        return buscaUsuarioService.listarUsuarios(pageable);
    }

    @GetMapping(path = "/{idUsuario}")
    public Usuario buscarUsuarioPorId(@PathVariable("idUsuario") Long id) {
        return buscaUsuarioService.buscarUsuarioPorId(id);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public Usuario buscarUsuarioPorCpf(@PathVariable("cpf") String cpf) {
        return buscaUsuarioService.buscarUsuarioPorCpf(cpf);
    }

    @PutMapping(path = "/{id}")
    public Usuario atualizarUsuario(
            @PathVariable(name = "id") Long id,
            @RequestBody @Valid AtualizarUsuarioRequest usuarioRequest
    ) {
        return atualizacaoUsuarioService.atualizarUsuario(id, usuarioRequest);
    }
}
