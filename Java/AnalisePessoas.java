//package Users.Matheus.Desktop.Programas.PrimeiroPrograma;

public class AnalisePessoas{
	
	public static int qtMulheres = 0;
	public static int qtHomens = 0;
	public static float soma = 0;
	public static float mediaHomens = 0;
	public static float maior = 0;
	public static float menor = 10;
	
	public static void analiseAltura(int s, float a, boolean vf){//método que analisa a altura de um conjunto de pessoas
		int sexo = s;
		float altura = a;
		if(vf == false){
			if(s == 1){
				qtMulheres++;
			}
			else{
				if(s == 2){
					qtHomens++;
					soma = soma + a;
				}
				else System.out.println("numero do sexo invalido");
			}	
			if(a > maior){
				maior = a;
			}
			if(a < menor){
				menor = a;
			}
		}	
		else{
			mediaHomens = soma/qtHomens;
			System.out.println("Maior altura:"+maior+" e menor:"+menor);
			System.out.println("A media dos homens:"+mediaHomens);
			System.out.println("A quantidade de mulheres:"+qtMulheres);
		}
	}

}