package com.app.project.strategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

public class ValidacaoNascimento implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        
        return cliente.getNascimento() != null && cliente.getNascimento().length() >= 10 && checkToday(cliente.getNascimento()) ? "" : "Data de nascimento inválida.";
    }

    public static boolean checkToday(String nascimento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			LocalDate date = LocalDate.parse(nascimento, formatter);
			LocalDate today = LocalDate.now();
			if(date.isBefore(today)) {
				return true; // Data de nascimento é antes da data atual
			} else {
				return false;
			}
		} catch (DateTimeParseException e) {
			return false; // Erro de conversão na data
		}
    }
}
