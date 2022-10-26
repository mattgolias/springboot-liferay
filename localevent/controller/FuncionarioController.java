package com.liferay.localevent.controller;

import com.liferay.localevent.dto.FuncionarioDto;
import com.liferay.localevent.model.Funcionario;
import com.liferay.localevent.repository.FuncionarioRepository;
import com.liferay.localevent.services.FuncionarioService;
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
@RequestMapping("/funcionario")
public class FuncionarioController {

    final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Object> saveFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {
        if(funcionarioService.existByEmail(funcionarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Email já está em uso!");
        }
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionario));
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneFuncionario(@PathVariable(value = "id") UUID id) {
        Optional<Funcionario> funcionarioOptional = funcionarioService.findById(id);
        if(funcionarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id") UUID id) {
        Optional<Funcionario> funcionarioOptional = funcionarioService.findById(id);
        if(funcionarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
        }
        funcionarioService.delete(funcionarioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFuncionario(@PathVariable(value = "id") UUID id, @RequestBody @Valid FuncionarioDto funcionarioDto) {
    Optional<Funcionario> funcionarioOptional = funcionarioService.findById(id);
    if(funcionarioOptional.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado");
    }
    var funcionario = new Funcionario();
    BeanUtils.copyProperties(funcionarioDto, funcionario);
    funcionario.setId(funcionarioOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.save(funcionario));
    }
}
