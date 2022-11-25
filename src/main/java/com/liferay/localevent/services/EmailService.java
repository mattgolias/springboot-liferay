package com.liferay.localevent.services;

import com.liferay.localevent.model.Email;
import com.liferay.localevent.repository.EmailRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Transactional
    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public boolean existByEmail(String email) {
        return emailRepository.existsByEmail(email);
    }

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Optional<Email> findById(UUID id) {
        return emailRepository.findById(id);
    }

    @Transactional
    public void delete(Email email) {
        emailRepository.delete(email);
    }
}
