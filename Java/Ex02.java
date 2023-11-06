//Faça um programa que conte de 10 a 3, mostrando na tela, e calcula a soma desses números. 

public class Ex02 {
    public static void main(String [] args){
        int contador = 10;
        int soma = 0;
        while(contador>2){
            System.out.println(contador);
            soma = soma + contador;
            contador--;
        }
        System.out.println("A soma dos números foi: "+soma);
    }
}
