// dao/ClienteDAO.java
package com.app.project.dao;

import com.app.project.model.Entidade;

import java.util.List;

public interface IDAO {
    void salvar(Entidade entidade);
    List<Entidade> buscar(Entidade entidade);
    void atualizar(Entidade entidade);
    void deletar(Entidade entidade);
}
