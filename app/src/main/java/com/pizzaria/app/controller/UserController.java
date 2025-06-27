package com.pizzaria.app.controller;

import com.pizzaria.app.entity.User;
import com.pizzaria.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User novoUser(@RequestBody User user) {
        return userService.novoUser(user);
    }

    @GetMapping
    public List<User> listarUsers() {
        return userService.listarUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUser(@PathVariable String id, @RequestBody User userAtualizado) {
        User user = userService.atualizarUser(id, userAtualizado);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarUser(@PathVariable String id, @ModelAttribute User userAtualizado, Model model) {
        userService.atualizarUser(id, userAtualizado);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deletarUser(@PathVariable String id, Model model) {
        userService.deletarUser(id);
        return "redirect:/users";
    }
}
