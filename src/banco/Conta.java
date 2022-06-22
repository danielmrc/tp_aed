package banco;

import fila.FilaOperacao;

public class Conta{
    private int numeroConta;
    private String cpf;
    private double saldo;
    private FilaOperacao operacoes;


    public Conta(){
        this.numeroConta = 0;
        this.cpf = null;
        this.saldo = 0;
        this.operacoes = new FilaOperacao();
    }

    public Conta(int numeroC, String c, double s){
        this.numeroConta = numeroC;
        this.cpf = c;
        this.saldo = s;
        this.operacoes = new FilaOperacao();
    }

    public int getNumeroConta(){
        return numeroConta;
    }

    public String getCpf(){
        return cpf;
    }

    public double getSaldo(){
        return saldo;
    }

    public void setNumeroConta(int numConta){
        this.numeroConta = numConta;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public void sacar(double valor){
        if(valor <= saldo)
            this.saldo = this.saldo - valor;
            else
                System.out.println("Saldo insuficiente para saque!!");
    }

    public void depositar(double valor){
        if(!(valor < 0))
            this.saldo = this.saldo + valor;
            else
                System.out.println("Não é possível depositar um valor negativo!");
    }

    public void setOperacoes(FilaOperacao operacoes){
        this.operacoes = operacoes;
    }

    public FilaOperacao getOperacoes(){
        return this.operacoes;
    }

}