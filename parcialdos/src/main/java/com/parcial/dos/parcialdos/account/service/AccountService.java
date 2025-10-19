package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements com.parcial.dos.parcialdos.account.service.IAccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // --- Implementación de Métodos de IAccountService ---

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO requestDTO) {
        // 1. Crear la Entidad Account a partir del DTO de Request
        Account newAccount = new Account();
        newAccount.setAccountNumber(requestDTO.getNumeroCuenta());
        newAccount.setOwnerName(requestDTO.getDueno());
        newAccount.setBalance(requestDTO.getBalanceActual());
        newAccount.setActive(true); // Una nueva cuenta siempre está activa

        // 2. Guardar en la base de datos
        Account savedAccount = accountRepository.save(newAccount);

        // 3. Convertir la Entidad guardada a DTO de Respuesta
        return mapToResponseDTO(savedAccount);
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        // 1. Obtener todas las entidades
        return accountRepository.findAll().stream()
                // 2. Mapear cada entidad a un ResponseDTO
                .map(this::mapToResponseDTO)
                // 3. Colectar en una lista
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountResponseDTO> getById(Long id) {
        // 1. Buscar la entidad por ID y luego mapearla si existe
        return accountRepository.findById(id).map(this::mapToResponseDTO);
    }

    @Override
    public Optional<String> updateBalance(Long id, BigDecimal newBalance) {
        // 1. Buscar la cuenta
        Optional<Account> accountOpt = accountRepository.findById(id);

        if (accountOpt.isEmpty()) {
            return Optional.of("Cuenta no encontrada");
        }

        Account account = accountOpt.get();
        BigDecimal oldBalance = account.getBalance();

        // 2. Actualizar solo el balance
        account.setBalance(newBalance);
        accountRepository.save(account);

        // 3. Devolver mensaje de éxito
        String message = String.format(
                "La cuenta %s fue actualizada: balanceAnterior=%.2f, balanceActual=%.2f",
                account.getAccountNumber(), oldBalance, newBalance
        );
        return Optional.of(message);
    }

    @Override
    public void delete(Long id) {
        // La eliminación de JPA es simple. La lógica de negocio para borrar/desactivar debe ir aquí.
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<AccountOwnerBalanceDTO> getByAccountNumber(String numeroCuenta) {
        // 1. Buscar por número de cuenta
        return accountRepository.findByAccountNumber(numeroCuenta)
                // 2. Mapear la entidad a DTO reducido si existe
                .map(this::mapToOwnerBalanceDTO);
    }

    // --- Mappers privados para conversión entre Entity y DTO ---

    private AccountResponseDTO mapToResponseDTO(Account account) {
        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setId(account.getId());
        dto.setNumeroCuenta(account.getAccountNumber());
        dto.setDueno(account.getOwnerName());
        dto.setBalanceActual(account.getBalance());
        dto.setActive(account.getActive());
        return dto;
    }

    private AccountOwnerBalanceDTO mapToOwnerBalanceDTO(Account account) {
        AccountOwnerBalanceDTO dto = new AccountOwnerBalanceDTO();
        dto.setDueno(account.getOwnerName());
        dto.setBalanceActual(account.getBalance());
        return dto;
    }
}