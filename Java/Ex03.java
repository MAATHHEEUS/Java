//Faça um programa que exiba todos os números ímpares entre 0 e 31.  

public class Ex03 {
    public static void main(String [] args){
        int contador = 0;
        while(contador<32){
            if((contador%2)!=0){
                System.out.println(contador);
            }
            contador++;
        }
    }
}
