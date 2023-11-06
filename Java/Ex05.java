import java.util.Scanner;

//Faça um programa que leia 500 valores e no final, informe quantos eram maiores que 100.   

public class Ex05 {
    public static void main(String [] args){
        int contador = 0;
        int maiores = 0;
        Scanner s = new Scanner(System.in);
        while(contador<500){
            int n = s.nextInt();
            if(n>100){
                maiores++;
            }
            contador++;
        }
        System.out.println("Foram digitados "+maiores+" números maiores que 100");
    }
}
