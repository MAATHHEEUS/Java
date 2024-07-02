package TesteNewGo;

// Classe Senha

public class Senha {
    
    // Construtor 
    public Senha(String c) {
        if(c == "" || !verificaConteudo(c)){
            System.out.println("Senha criada sem conteudo!");
            this.conteudo = "";
        }else{
            this.conteudo = c;    
        }
    }
    
    // Atributo
    private String conteudo = "";
    
    // Get's e Set's
    public void setConteudo(String c) {
        if(verificaConteudo(c)){
            this.conteudo = c;    
        }else{
            System.out.println("Não foi possível atualizar a senha!");
        }
    }
    
    public String getConteudo() {
        return this.conteudo;
    }
    
    // Métodos
    private boolean verificaConteudo(String c){
        // Verifica quantidade de caracteres do conteudo
        if(c.length() > 30 || c.length() < 10){
            System.out.println("Senha com quantidade de caracteres errado! Deve conter entre 10 até 30 caracteres!");
            return false;
        }
        
        // Tranforma em array para verificar os requisitos da senha
        char[] caracteres = c.toCharArray();
        boolean maiuscula = false;
        boolean minuscula = false;
        boolean especial = false;
        boolean numero = false;
        
        // Verifica cada caracter do conteudo
        for(int i = 0; i < caracteres.length; i++){
            if(Character.isUpperCase(caracteres[i])){
                maiuscula = true;
            }
            if(Character.isLowerCase(caracteres[i])){
                minuscula = true;
            }
            if(Character.isDigit(caracteres[i])){
                numero = true;
            }
            if(!Character.isLowerCase(caracteres[i]) && !Character.isUpperCase(caracteres[i]) && !Character.isDigit(caracteres[i])){
                especial = true;
            }
            
        }
        
        // Retorno
        if(maiuscula && minuscula && numero && especial){
            return true;
        }else{
            System.out.println("Senha não atende aos requisitos! Deve conter Letra Maiúscula, Minúscula, Caracter Especial e Dígito!");
            return false;
        }
    }
    
    // Retorna a força da senha com base no seu tamanho
    public int forcaSenha(String c){
        int forca = 70;
        int tamanho = c.length();
        for(int i = 10; i <= 30; i++){
            if(tamanho == i){
                forca += i;
            }
        }
        return forca;
    }
}
