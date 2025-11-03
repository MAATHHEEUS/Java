package com.app.project.strategy;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoSenha implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        
        return cliente.getSenha() != null && cliente.getSenha().length() >= 8 && containsSpecialCharacters(cliente.getSenha()) && containsLowercase(cliente.getSenha()) && containsUppercase(cliente.getSenha()) ? "" : "Senha inválida.";
    }

    public static boolean containsSpecialCharacters(String inputString) {
        // This regex matches any character that is NOT a letter (a-z, A-Z),
        // a digit (0-9), or a whitespace character.
        // The Pattern.CASE_INSENSITIVE flag makes the letter matching case-insensitive.
        Pattern p = Pattern.compile("[^a-zA-Z0-9\\s]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(inputString);
        return m.find(); // Returns true if a match is found, indicating special characters
    }

    public static boolean containsUppercase(String str) {
        for (char ch : str.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true; // Found an uppercase letter
            }
        }
        return false; // No uppercase letters found
    }

    public static boolean containsLowercase(String str) {
        for (char ch : str.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                return true; // Found an uppercase letter
            }
        }
        return false; // No uppercase letters found
    }
}
