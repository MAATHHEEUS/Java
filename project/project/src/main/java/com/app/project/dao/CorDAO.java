package com.app.project.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.app.project.model.Entidade;

@Repository
public class CorDAO implements IDAO  {
    
    @Override
    public void salvar(Entidade entidade) {}

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        return null;
    }

    @Override
    public void atualizar(Entidade entidade) {}

    @Override
    public void deletar(Entidade entidade) {}
}
