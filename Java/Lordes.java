import java.util.Random;
import java.util.Scanner;

public class Lordes{
	
	public static int soldados;
	public static int inimigos;
	public static int esquerda;
	public static int direita;
	
	public static void Jogar(){
		System.out.println("BEM VINDO AO LORDES!!!");
		
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		soldados = r.nextInt(10)+1;
	
		while (true){
			inimigos = r.nextInt(soldados)+1;
			inimigos = inimigos*3;
			System.out.println("Voce tem "+soldados+" soldados");
			System.out.println("inimigos: "+inimigos+"\n");
			direita = r.nextInt(9)+1;
			esquerda = r.nextInt(9)+1;
			System.out.println(">: x"+direita);
			System.out.println("<: x"+esquerda);
			String escolha = s.next();
			if(escolha.equals("<")){
				soldados = soldados*esquerda;
				System.out.println("Agora vc tem "+soldados+" soldados");
			}else{
				soldados = soldados*direita;
				System.out.println("Agora vc tem "+soldados+" soldados");
			}
			System.out.println("DUELANDO...");
			try{
				Thread.sleep((soldados+inimigos));
			}
			catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			int duelo = inimigos-soldados;
			if(duelo >= 0){
				System.out.println("QUE PENA VOCÊ PERDEU!!!");
				inimigos = inimigos-soldados;
				System.out.println("Restaram "+inimigos+" inimigos");
				break;
			}else{
				soldados = soldados-inimigos;
				System.out.println("Restaram "+soldados+" soldados\n");
			}
			
		}
		s.close();
	}
}