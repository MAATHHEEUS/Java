package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Pedido;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    List<Pedido> findByStatus(String status);
}
