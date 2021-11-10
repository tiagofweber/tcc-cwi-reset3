package io.github.cwireset.tcc.controller;

import io.github.cwireset.tcc.domain.Periodo;
import io.github.cwireset.tcc.domain.Reserva;
import io.github.cwireset.tcc.request.CadastrarReservaRequest;
import io.github.cwireset.tcc.response.InformacaoReservaResponse;
import io.github.cwireset.tcc.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<Reserva> listarReservasPorSolicitante(@PathVariable Long idSolicitante, @Valid Periodo periodo) {
        return reservaService.listarReservasPorSolicitante(idSolicitante, periodo);
    }
}
