package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Usuario;
import io.github.cwireset.tcc.request.AtualizarUsuarioRequest;
import io.github.cwireset.tcc.service.usuario.AtualizarUsuarioService;
import io.github.cwireset.tcc.service.usuario.BuscarUsuarioService;
import io.github.cwireset.tcc.service.usuario.CadastrarUsuarioService;
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
    private CadastrarUsuarioService cadastrarUsuarioService;
    @Autowired
    private AtualizarUsuarioService atualizarUsuarioService;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody @Valid Usuario usuario) {
        return cadastrarUsuarioService.criarUsuario(usuario);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(@PageableDefault(sort = "nome") @ApiIgnore Pageable pageable) {
        return buscarUsuarioService.listarUsuarios(pageable);
    }

    @GetMapping(path = "/{idUsuario}")
    public Usuario buscarUsuarioPorId(@PathVariable("idUsuario") Long id) {
        return buscarUsuarioService.buscarUsuarioPorId(id);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public Usuario buscarUsuarioPorCpf(@PathVariable("cpf") String cpf) {
        return buscarUsuarioService.buscarUsuarioPorCpf(cpf);
    }

    @PutMapping(path = "/{id}")
    public Usuario atualizarUsuario(
            @PathVariable Long id,
            @RequestBody @Valid AtualizarUsuarioRequest usuarioRequest
    ) {
        return atualizarUsuarioService.atualizarUsuario(id, usuarioRequest);
    }
}
