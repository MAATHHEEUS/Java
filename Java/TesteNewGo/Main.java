package TesteNewGo;

public class Main
{
	public static void main(String[] args) {
	    Senha s = new Senha("Ma_9flsbdflsbdf");
		System.out.println(s.getConteudo());
		System.out.println(s.forcaSenha(s.getConteudo()));
	}
}
