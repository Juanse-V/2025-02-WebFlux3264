package com.parcial.dos.parcialdos.account.controller;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final IAccountService service;

    // Inyección de dependencia del servicio (Constructor Injection)
    public AccountController(IAccountService service) {
        this.service = service;
    }

    // POST /api/accounts - Crea una cuenta
    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO request) {
        // Llama al servicio para crear la cuenta
        AccountResponseDTO createdAccount = service.createAccount(request);

        // Retorna 201 Created y el objeto creado
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // GET /api/accounts - Obtiene todas las cuentas
    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> getAll() {
        // Llama al servicio para obtener todas las cuentas
        List<AccountResponseDTO> accounts = service.getAll();

        // Retorna 200 OK y la lista
        return ResponseEntity.ok(accounts);
    }

    // GET /api/accounts/{id} - Obtiene una cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Long id) {
        // Llama al servicio para buscar por ID
        Optional<AccountResponseDTO> account = service.getById(id);

        // Retorna 200 OK si existe, o 404 Not Found si no existe
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT /api/accounts/{id} - Actualiza únicamente el balanceActual
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AccountRequestDTO request) {
        // El RequestDTO solo se usa para obtener el nuevo balance
        BigDecimal newBalance = request.getBalanceActual();

        // Llama al servicio para actualizar el balance
        Optional<String> result = service.updateBalance(id, newBalance);

        // Si el resultado está presente, devuelve el mensaje de éxito o de cuenta no encontrada
        if (result.isPresent()) {
            String message = result.get();

            // Si el servicio devuelve "Cuenta no encontrada"
            if (message.equals("Cuenta no encontrada")) {
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            // Si el servicio devuelve el mensaje de actualización exitosa
            return ResponseEntity.ok(message);
        }

        // En caso de un error inesperado (aunque el Optional.of() en el servicio lo minimiza)
        return new ResponseEntity<>("Error desconocido al actualizar el balance.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // DELETE /api/accounts/{id} - Borra la cuenta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Llama al servicio para eliminar
        service.delete(id);

        // Retorna 204 No Content (el estándar para eliminación exitosa)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // GET /api/accounts/by-number/{numeroCuenta} - Busca por numeroCuenta (DTO reducido)
    @GetMapping("/by-number/{numeroCuenta}")
    public ResponseEntity<AccountOwnerBalanceDTO> getByNumeroCuenta(@PathVariable String numeroCuenta) {
        // Llama al servicio para buscar por número de cuenta
        Optional<AccountOwnerBalanceDTO> accountInfo = service.getByAccountNumber(numeroCuenta);

        // Retorna 200 OK si existe, o 404 Not Found si no existe
        return accountInfo.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}