package banco;
public class Conta{
    private int numeroConta;
    private String cpf;
    private double saldo;


    public Conta(){
        this.numeroConta = 0;
        this.cpf = null;
        this.saldo = 0;
    }

    public Conta(int numeroC, String c, double s){
        this.numeroConta = numeroC;
        this.cpf = c;
        this.saldo = s;
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

}