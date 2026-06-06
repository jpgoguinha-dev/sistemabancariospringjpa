package com.sistema.demo.controller;

import com.sistema.demo.model.Conta;
import com.sistema.demo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    // 1. Cadastrar uma nova conta bancária
    @PostMapping("/contas")
    public Conta criarConta(@RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    // 2. Listar todas as contas salvas no banco
    @GetMapping("/contas")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    // 3. Buscar uma conta específica pelo número
    @GetMapping("/contas/{numero}")
    public Conta buscarPorNumero(@PathVariable String numero) {
        return contaRepository.findById(numero).orElse(null);
    }

    // 4. Rota para realizar saques gravando no banco de dados
    @PutMapping("/contas/{numero}/sacar/{valor}")
    public String realizarSaque(@PathVariable String numero, @PathVariable Double valor) {
        // Busca a conta no banco de dados
        Conta conta = contaRepository.findById(numero).orElse(null);
        
        if (conta == null) {
            return "Erro: Conta nao encontrada.";
        }

        // Executa a lógica de validação que criamos na classe Conta
        boolean realizouSaque = conta.sacar(valor);

        if (realizouSaque) {
            contaRepository.save(conta); // Atualiza os dados da conta no banco!
            return "Saque de R$ " + valor + " realizado com sucesso! Novo saldo: R$ " + conta.getSaldo();
        } else {
            return "Erro: Falha ao realizar saque. Verifique se ha saldo suficiente.";
        }
    }

    // 5. Rota para realizar depósitos gravando no banco de dados
    @PutMapping("/contas/{numero}/depositar/{valor}")
    public String realizarDeposito(@PathVariable String numero, @PathVariable Double valor) {
        Conta conta = contaRepository.findById(numero).orElse(null);
        
        if (conta == null) {
            return "Erro: Conta nao encontrada.";
        }

        if (valor > 0) {
            conta.depositar(valor);
            contaRepository.save(conta); // Grava o novo saldo no banco!
            return "Deposito de R$ " + valor + " realizado com sucesso! Novo saldo: R$ " + conta.getSaldo();
        } else {
            return "Erro: O valor do deposito deve ser maior que zero.";
        }
    }
}
