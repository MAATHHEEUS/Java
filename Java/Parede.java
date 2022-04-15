//package Users.Matheus.Desktop.Programas.PrimeiroPrograma;
public class Parede{

	public static void azulejos(double hp, double lp, double ha, double la){ // dado altura e largura de uma parede calcula quantos azulejos serão necessarios
		double areaP = hp * lp;
		double areaA = ha * la;
		double azulejo = areaP / areaA;
		System.out.println("serão necessarios "+String.format("%.2f", azulejo)+" azulejos");
	}
	
}