package com.trocabook.Trocabook.controllers;

import com.trocabook.Trocabook.service.IDadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {
    private final IDadosService dadosService;

    @Autowired
    public AdminController(IDadosService dadosService) {
        this.dadosService = dadosService;
    }

    @GetMapping("/admin/dashboard-pagina")
    public String dashboard() {
        return "admin/dashboard"; // <-- CORRIGIDO
    }

    @GetMapping("listaUsuarios-pagina")
    public String listaUsuarios() {
        return "admin/listaUsuarios"; // <-- CORRIGIDO
    }

    @GetMapping("cadastroAdmin")
    public String cadastroAdmin() {
        return "admin/cadastroAdmin"; // <-- CORRIGIDO
    }

    @GetMapping("loginAdmin")
    public String loginAdmin() {
        return "admin/loginAdmin"; // <-- CORRIGIDO
    }

    @GetMapping("adminHome")
    public String adminHome() {
        return "admin/indexAdmin"; // <-- CORRIGIDO
    }

    @GetMapping("/alterarUsuario/{id}")
    public String alterarPagina(@PathVariable int id, Model model) {
        model.addAttribute("usuario", dadosService.obterUsuario(id));
        return "admin/alterarUsuario"; // <-- CORRIGIDO
    }
}