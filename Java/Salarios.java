//package Users.Matheus.Desktop.Programas.PrimeiroPrograma;
	
public class Salarios{	
	//variaveis
	public static double salario = 0;
	public static int nFuncionario = 0;
	
	public static void mediaSalarios(double s, boolean vf){//programa que calcule a média de salários de uma empresa, pedindo ao usuário a grade de funcionários e os salários, e devolvendo a média salarial
		if(vf == false){
			nFuncionario++;
			salario += s;
		}
		else{
			System.out.println("A media de salarios é R$ : "+String.format("%.2f", (salario/nFuncionario)));
		}
	}
	
}	
	