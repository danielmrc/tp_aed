package banco;

import data.Data;


public class Operacao {
    private int numConta;
    private int codOperacao;
    private double valor;
    private Data data;

    public Operacao() {
        this.numConta = 0;
        this.codOperacao = 0;
        this.valor = 0;
        this.data = null;
    }

    public Operacao(int numConta, int codOperacao, double valor, Data data){
        this.numConta = numConta;
        this.codOperacao = codOperacao;
        this.valor = valor;
        this.data = data;
    }

    public void setNumConta(int numConta){
        this.numConta = numConta;
    }

    public void setCodOperacao(int codOperacao){
        this.codOperacao = codOperacao;
    }

    public void setValor(double valor){
        this.valor = valor;
    }

    public void setData(Data data){
        this.data = data;
    }

    public int getNumConta(){
        return this.numConta;
    }

    public int getCodOperacao(){
        return this.codOperacao;
    }

    public double getValor(){
        return this.valor;
    }

    public Data getData(){
        return this.data;
    }
    
}
