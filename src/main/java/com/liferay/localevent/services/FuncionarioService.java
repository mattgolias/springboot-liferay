package com.liferay.localevent.services;

import com.liferay.localevent.model.Funcionario;
import com.liferay.localevent.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public boolean existByEmail(String email) {
        return funcionarioRepository.existsByEmail(email);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }

    @Transactional
    public void delete(Funcionario funcionario) {
        funcionarioRepository.delete(funcionario);
    }
}
