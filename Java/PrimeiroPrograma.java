//package Users.Matheus.Desktop.Programas.PrimeiroPrograma;
import java.util.Scanner;

public class PrimeiroPrograma{
	
	public static void main(String args[]){
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Primeiro Programa");
		
		System.out.println("escolha qual programa rodar!");
		System.out.println("1 = conversor de graus cº");
		System.out.println("2 = analisa pessoas");
		System.out.println("3 = analisa funcionario");
		System.out.println("4 = calcula azulejos para parede");
		System.out.println("5 = analisa matriz quadrada");
		System.out.println("6 = Jogar Lordes");
		System.out.println("7 = sair");
		
		int escolha = entrada.nextInt();
		
		switch(escolha){
			case 1:
				System.out.println("digite a temperatura em ºc");
				double c = entrada.nextDouble();
				Conversor.converteGraus(c);
				break;
			case 2:
				System.out.println("digite quantas pessoas");
				int n = entrada.nextInt();
				for(int i=0; i<n; i++){
					System.out.println("digite o sexo 1-mulher 2-homem");	
					int s = entrada.nextInt();
					System.out.println("digite a altura");
					float a = entrada.nextFloat();
					AnalisePessoas.analiseAltura(s, a, false);
				}
				AnalisePessoas.analiseAltura(0, 0, true);
				break;
			case 3:
				System.out.println("Digite quantos funcionarios");
				int f = entrada.nextInt();
				for(int i=1; i<f+1; i++){
					System.out.println("digite o salario do funcionario "+i);
					double a = entrada.nextDouble();
					Salarios.mediaSalarios(a, false);
				}
				Salarios.mediaSalarios(0, true);
				break;
			case 4:
				System.out.println("digite a altura da parede:");
				double hp = entrada.nextDouble();
				System.out.println("digite a largura da parede:");
				double lp = entrada.nextDouble();
				System.out.println("digite a altura do azulejo:");
				double ha = entrada.nextDouble();
				System.out.println("digite a largura do azulejo:");
				double la = entrada.nextDouble();
				Parede.azulejos(hp, lp, ha, la);
				break;
			case 5:
				System.out.println("digite o tamanho da matriz quadrada");
				int x = entrada.nextInt();
				Matriz.maiorValor(Matriz.inicializaMatriz(x));
				break;
			case 6:
				Lordes.Jogar();
			default:
				break;
		}
			
		entrada.close();
	}
}	