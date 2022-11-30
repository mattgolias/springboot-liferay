package com.liferay.localevent.controller;


import com.liferay.localevent.dto.EventoDto;
import com.liferay.localevent.model.Evento;
import com.liferay.localevent.services.EventoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/evento")
public class EventoController {

    final EventoService eventoService;

    public EventoController(EventoService eventoService){
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveEvento(@RequestBody @Valid EventoDto eventoDto) {
        if(eventoService.existByDataAndLocalAndHorarioInicio(eventoDto.getData(), eventoDto.getLocal(), eventoDto.getHorarioInicio())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Horário indisponível");
        }
        var evento = new Evento();
        BeanUtils.copyProperties(eventoDto, evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.save(evento));
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        return ResponseEntity.status(HttpStatus.OK).body(eventoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEvento(@PathVariable(value = "id") UUID id) {
        Optional<Evento> eventoOptional = eventoService.findById(id);
        if(eventoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(eventoOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEvento(@PathVariable(value = "id") UUID id) {
        Optional<Evento> eventoOptional = eventoService.findById(id);
        if(eventoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
        }
        eventoService.delete(eventoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Evento deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEvento(@PathVariable(value = "id") UUID id, @RequestBody @Valid EventoDto eventoDto) {
        Optional<Evento> eventoOptional = eventoService.findById(id);
        if(eventoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado");
        }
        var evento = new Evento();
        BeanUtils.copyProperties(eventoDto, evento);
        evento.setId(eventoOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(eventoService.save(evento));
    }
}
