package com.parcial.dos.parcialdos.account.dto;


import java.math.BigDecimal;

// Respuesta reducida (ya tienes los campos en tu captura)
public class AccountOwnerBalanceDTO {

    private String dueno;
    private BigDecimal balanceActual;

    // --- Getters and Setters ---
    // (Asegúrate de tener Getters y Setters aquí)

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
}