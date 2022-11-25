package com.liferay.localevent.controller;

import com.liferay.localevent.dto.EmailDto;
import com.liferay.localevent.model.Email;
import com.liferay.localevent.services.EmailService;
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
@RequestMapping("/email")
public class EmailController {

    final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<Object> saveEmail(@RequestBody @Valid EmailDto emailDto) {
        if(emailService.existByEmail(emailDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Email já está em uso!");
        }
        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(emailService.save(email));
    }

    @GetMapping
    public ResponseEntity<List<Email>> getAllEmails() {
        return ResponseEntity.status(HttpStatus.OK).body(emailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value = "id") UUID id) {
        Optional<Email> emailOptional = emailService.findById(id);
        if(emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(emailOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmail(@PathVariable(value = "id") UUID id) {
        Optional<Email> emailOptional = emailService.findById(id);
        if(emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado");
        }
        emailService.delete(emailOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Email deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmail(@PathVariable(value = "id") UUID id, @RequestBody @Valid EmailDto emailDto) {
        Optional<Email> emailOptional = emailService.findById(id);
        if(emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado");
        }
        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        email.setId(emailOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(emailService.save(email));
    }
}
