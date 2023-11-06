//Faça um programa que exiba todos os números múltiplos de 5, entre 1000 e 2000  . 

public class Ex04 {
    public static void main(String [] args){
        int contador = 1000;
        while(contador<2001){
            System.out.println(contador);
            contador = contador+5;
        }
    }
}
