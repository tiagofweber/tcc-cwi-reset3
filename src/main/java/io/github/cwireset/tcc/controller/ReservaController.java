package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.FormaPagamento;
import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InformacaoReservaResponse realizarReserva(@RequestBody @Valid CadastrarReservaRequest reservaRequest) {
        return reservaService.realizarReserva(reservaRequest);
    }

    @GetMapping(path = "/solicitantes/{idSolicitante}")
    public Page<Reserva> listarReservasPorSolicitante(
            @PathVariable Long idSolicitante,
            @Valid Periodo periodo,
            @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable
            ) {
        return reservaService.listarReservasPorSolicitante(idSolicitante, periodo, pageable);
    }

    @GetMapping(path = "/anuncios/anunciantes/{idAnunciante}")
    public List<Reserva> listarReservasPorAnunciante(
            @PathVariable(value = "idAnunciante") Long idAnunciante,
            @PageableDefault(sort = "periodoDataHoraFinal", direction = Sort.Direction.DESC) @ApiIgnore Pageable pageable
    ) {
        return reservaService.listarReservasPorAnunciante(idAnunciante, pageable);
    }

    @PutMapping(path = "/{idReserva}/pagamentos")
    public void pagarReserva(@PathVariable Long idReserva, @RequestBody FormaPagamento formaPagamento) {
        reservaService.pagarReserva(idReserva, formaPagamento);
    }
}
