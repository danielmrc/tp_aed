package banco;

public class Admin {
    private String cpf;
    private String nome;


    public Admin(){
        this.cpf = null;
        this.nome = null;
    }

    public Admin(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
