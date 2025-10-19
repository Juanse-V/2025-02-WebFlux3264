// IAccountService.java (CORREGIDO)
package com.parcial.dos.parcialdos.account.service;

import java.math.BigDecimal; // Necesitas este import!
import java.util.List;
import java.util.Optional; // Necesitas este import!

import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;

public interface IAccountService {
    // 1. Coincide con el Service:
    AccountResponseDTO createAccount(AccountRequestDTO requestDTO);

    // 2. Coincide con el Controller (y Service)
    List<AccountResponseDTO> getAll();

    // 3. Coincide con el Controller (y Service) - Usa Optional:
    Optional<AccountResponseDTO> getById(Long id);

    // 4. Coincide con el Controller (y Service) - Usa Optional<String> y BigDecimal:
    Optional<String> updateBalance(Long id, BigDecimal newBalance);

    // 5. Coincide con el Controller (y Service)
    void delete(Long id);

    // 6. Coincide con el Controller (y Service) - Usa Optional:
    Optional<AccountOwnerBalanceDTO> getByAccountNumber(String numeroCuenta);
}