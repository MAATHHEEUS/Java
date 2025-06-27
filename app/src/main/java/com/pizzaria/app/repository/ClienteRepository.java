package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Cliente;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    List<Cliente> findByNome(String nome);
}
