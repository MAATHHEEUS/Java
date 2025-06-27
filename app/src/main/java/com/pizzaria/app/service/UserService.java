package com.pizzaria.app.service;

import com.pizzaria.app.entity.User;
import com.pizzaria.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User novoUser(User user) {
        return userRepository.save(user);
    }

    public List<User> listarUsers() {
        return userRepository.findAll();
    }

    public User atualizarUser(String id, User userAtualizado) {
        return userAtualizado;
    }

    public String deletarUser(String id){
        return "User deletado";
    }
}
