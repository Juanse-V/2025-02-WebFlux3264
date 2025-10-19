package com.parcial.dos.parcialdos.account.dto;

import java.math.BigDecimal;


public class AccountResponseDTO {

    private Long id;
    private String numeroCuenta;
    private String dueno;
    private BigDecimal balanceActual;
    private Boolean active;

    // --- Getters and Setters (GENERADOS) ---

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    // ... y así sucesivamente para todas las otras variables ...
    public String getNumeroCuenta() { // <-- Este método es el que le falta a tu código
        return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) { // <-- Este método es el que le falta a tu código
        this.numeroCuenta = numeroCuenta;
    }
    // ...

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public BigDecimal getBalanceActual() {
        return balanceActual;
    }

    public void setBalanceActual(BigDecimal balanceActual) {
        this.balanceActual = balanceActual;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}