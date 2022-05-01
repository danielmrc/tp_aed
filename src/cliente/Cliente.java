package cliente;

import lista.ListaConta;

public class Cliente {
    private String cpf;
    private String nome;
    private ListaConta contas;

    public Cliente(){
        this.cpf = null;
        this.nome = null;
        this.contas = null;
    }

    public Cliente(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

    public Cliente(String cpf, String nome, ListaConta contas){
        this.cpf = cpf;
        this.nome = nome;
        this.contas = contas;
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

    public void setContas(ListaConta contas){
        this.contas = contas;
    }

    public ListaConta getContas(){
        return this.contas;
    }
}
