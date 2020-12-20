package br.com.fiap.api.controller;

import br.com.fiap.api.dto.AgendamentoCountDTO;
import br.com.fiap.api.model.AgendamentoModel;
import br.com.fiap.api.repository.AgendamentoRepository;
import br.com.fiap.api.utils.FindAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgendamentoController {
    @Autowired
    private AgendamentoModel agendamentoModel;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping("/agendamento")
    public ResponseEntity<AgendamentoModel> addAgendamento(@RequestBody AgendamentoModel agendamento){

        List<AgendamentoModel> dbAgendamento = agendamentoRepository.findByIdPessoa(agendamento.getIdPessoa());
        Boolean confere = new FindAgendamento().findAgendamento(agendamento.getIdCulto(), dbAgendamento);
        if (!confere) {
            agendamentoModel = agendamentoRepository.save(agendamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/agendamentos")
    public List<AgendamentoModel> getAgendamentos(){return agendamentoRepository.findAll();}

    @GetMapping("/agendamentos/{idCulto}")
    public List<AgendamentoModel> getAgendamentoByIdCulto(@PathVariable String idCulto) {
        return agendamentoRepository.findByIdCulto(idCulto);
    }

    @GetMapping("/agendamentos/{idCulto}/count")
    public ResponseEntity<AgendamentoCountDTO> getCount(@PathVariable String idCulto) {
        List<AgendamentoModel> dbAgendamentos = agendamentoRepository.findByIdCulto(idCulto);
        Integer count = dbAgendamentos.size();
        System.out.println(count);
        AgendamentoCountDTO countReturn = new AgendamentoCountDTO();
        countReturn.setCount(count);
        return ResponseEntity.status(HttpStatus.OK).body(countReturn);
    }

    //@GetMapping("/agendamento/{id}")
}
