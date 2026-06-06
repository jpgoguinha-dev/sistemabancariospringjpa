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

    // Rota para cadastrar uma nova conta no banco de dados de verdade
    @PostMapping("/contas")
    public Conta criarConta(@RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    // Rota para listar todas as contas salvas no banco
    @GetMapping("/contas")
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    // Rota para buscar uma conta específica pelo número
    @GetMapping("/contas/{numero}")
    public Conta buscarPorNumero(@PathVariable String numero) {
        return contaRepository.findById(numero).orElse(null);
    }
}
