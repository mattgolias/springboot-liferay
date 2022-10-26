package com.liferay.localevent.services;

import com.liferay.localevent.model.Evento;
import com.liferay.localevent.repository.EventoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventoService {
    final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    @Transactional
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }


    public boolean existByDataAndLocalAndHorarioInicial(String data, String local, String horarioInicial) {
        return eventoRepository.existsByDataAndLocalAndHorarioInicial(data, local, horarioInicial);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> findById(UUID id) {
        return eventoRepository.findById(id);
    }
}
