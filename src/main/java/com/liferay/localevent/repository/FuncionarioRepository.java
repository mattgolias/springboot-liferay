package com.liferay.localevent.repository;

import com.liferay.localevent.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    boolean existsByEmailFunc(String emailFunc);
}
