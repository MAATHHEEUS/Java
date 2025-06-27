package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Produto;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByNome(String nome);
}
