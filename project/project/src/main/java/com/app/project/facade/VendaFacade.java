// facade/VendaFacade.java
package com.app.project.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.project.dao.PagamentoDAO;
import com.app.project.dao.ProdutoVendaDAO;
import com.app.project.dao.StatusDAO;
import com.app.project.dao.VendaDAO;
import com.app.project.model.Entidade;
import com.app.project.model.Pagamento;
import com.app.project.model.ProdutoVenda;
import com.app.project.model.Venda;
import com.app.project.strategy.IValidador;

@Service
public class VendaFacade {

    @Autowired
    private VendaDAO vendaDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private PagamentoDAO pagamentoDAO;

    @Autowired
    private ProdutoVendaDAO pvDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public VendaFacade(){
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarVenda(Venda venda) {
        for (IValidador validador : validadores) {
            if (validador.validar(venda) != "") {
                return "Falha na validação: " + validador.validar(venda);
            }
        }
        try {
            // Salva o status do venda para ter o id
            statusDAO.salvar(venda.getStatus());

            // Salva o venda
            venda.getStatus().setId(venda.getStatus().getId());
            vendaDAO.salvar(venda);

            // Salva os pagamentos
            if (venda.getPagamentos() != null && !venda.getPagamentos().isEmpty()){
                for(Pagamento p : venda.getPagamentos()){
                    p.setVendaId(Integer.parseInt(venda.getId()));
                    pagamentoDAO.salvar(p);
                }
            }
            // Os produtos da venda
            if (venda.getProdutos() != null && !venda.getProdutos().isEmpty()){
                for(ProdutoVenda pv : venda.getProdutos()){
                    pv.setVendaId(Integer.parseInt(venda.getId()));
                    pvDAO.salvar(pv);
                }
            }
            return "Venda cadastrada com sucesso! #" + venda.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar venda: " + e.getMessage();
        }
    }

    public List<Entidade> listarVendas(Venda venda) {
        return vendaDAO.buscar(venda);
    }

    public String atualizarVenda(Venda venda) {
        for (IValidador validador : validadores) {
            if (validador.validar(venda) != "") {
                return "Falha na validação: " + validador.validar(venda);
            }
        }
        vendaDAO.atualizar(venda);
        return "Venda atualizada. #" + venda.getId();
    }

    public String removerVenda(Venda venda) {
        try {
            vendaDAO.deletar(venda);
            return "Venda inativada com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar venda: " + e.getMessage();
        }
    }
}
