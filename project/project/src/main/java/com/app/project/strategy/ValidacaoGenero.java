package com.app.project.strategy;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

public class ValidacaoGenero implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        return cliente.getGenero().getNome() != null && !cliente.getGenero().getNome().isEmpty() && containsAnyChar(cliente.getGenero().getNome()) ? "" : "Gênero inválido.";
    }

    // Verifica se o genero está de acordo com os ids de genero no banco
    public static boolean containsAnyChar(String text) {
        char[] searchChars1 = {'1', '2', '3'}; // 1 - Masculino, 2 - Feminino, 3 - Outro

        for (char c : searchChars1) {
            if (text.indexOf(c) != -1) { // If char c is found in text
                return true;
            }
        }
        return false;
    }
}
