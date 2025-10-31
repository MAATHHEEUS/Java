// facade/ImagemFacade.java
package com.app.project.facade;

import com.app.project.dao.ImagemDAO;
import com.app.project.model.Imagem;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagemFacade {
    @Autowired
    private ImagemDAO imagemDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public ImagemFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoImagem());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarImagem(Imagem imagem) {
        for (IValidador validador : validadores) {
            if (validador.validar(imagem) != "") {
                return "Falha na validação: " + validador.validar(imagem);
            }
        }
        try {
            imagemDAO.salvar(imagem);

            return "Imagem cadastrada com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar imagem: " + e.getMessage();
        }
    }

    public List<Entidade> listarImagens(Imagem imagem) {
        return imagemDAO.buscar(imagem);
    }

    public String atualizarImagem(Imagem imagem) {
        for (IValidador validador : validadores) {
            if (validador.validar(imagem) != "") {
                return "Falha na validação: " + validador.validar(imagem);
            }
        }
        imagemDAO.atualizar(imagem);
        return "Imagem atualizada.";
    }

    public String removerImagem(Imagem imagem) {
        try {
            imagemDAO.deletar(imagem);
            return "Imagem deletada com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao deletar imagem: " + e.getMessage();
        }
    }
}
