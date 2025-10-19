package com.parcial.dos.parcialdos.account.repository;

import com.parcial.dos.parcialdos.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Extiende JpaRepository con la Entidad (Account) y el tipo de su clave primaria (Long)
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Método necesario para el GET /by-number/{numeroCuenta}
    Optional<Account> findByAccountNumber(String accountNumber);
}