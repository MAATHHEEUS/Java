package com.pizzaria.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.service.ClienteService;
import com.pizzaria.app.entity.Pedido;
import com.pizzaria.app.service.PedidoService;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.service.ProdutoService;
import com.pizzaria.app.entity.User;
import com.pizzaria.app.service.UserService;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("layout");
        String fragment = "home :: content";
        log.info("Carregando fragmento: {}", fragment); // Log para depuração
        modelAndView.addObject("content", fragment);
        return modelAndView;
    }

    @GetMapping("/clientes")
    public String clientes(Model model) {
        List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        String fragment = "clientes :: content";
        log.info("Carregando fragmento: {}", fragment); // Log para depuração
        model.addAttribute("content", fragment);
        return "clientes";
    }

    @GetMapping("/pedidos")
    public String pedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        String fragment = "pedidos :: content";
        log.info("Carregando fragmento: {}", fragment); // Log para depuração
        model.addAttribute("content", fragment);
        return "pedidos";
    }

    @GetMapping("/produtos")
    public String produtos(Model model) {
        List<Produto> produtos = produtoService.listarProdutos();
        model.addAttribute("produtos", produtos);
        String fragment = "produtos :: content";
        log.info("Carregando fragmento: {}", fragment); // Log para depuração
        model.addAttribute("content", fragment);
        return "produtos";
    }
    
    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.listarUsers();
        model.addAttribute("users", users);
        String fragment = "users :: content";
        log.info("Carregando fragmento: {}", fragment); // Log para depuração
        model.addAttribute("content", fragment);
        return "users";
    }
}
