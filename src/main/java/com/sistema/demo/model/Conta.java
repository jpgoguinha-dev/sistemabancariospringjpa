package com.sistema.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Conta {
    @Id
    private String numero;
    private String titular;
    private Double saldo;

    public Conta() {}

    public Conta(String numero, String titular, Double saldoInicial) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(Double valor) {
        if (valor > 0) this.saldo += valor;
    }

    public boolean sacar(Double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            return true; 
        }
        return false; 
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
}
