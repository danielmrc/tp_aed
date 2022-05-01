package cliente;

public class Cliente {
    private String cpf;
    private String nome;

    public Cliente(){
        this.cpf = null;
        this.nome = null;
    }

    public Cliente(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return this.cpf;
    }

    public String getNome(){
        return this.nome;
    }
}
