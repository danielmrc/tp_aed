package lista;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;

import banco.Operacao;
import data.Data;
import banco.Conta;
import exception.NotFoundAccountException;

public class ListaConta {
    private ElementoConta primeiro;
    private ElementoConta ultimo;


    public ListaConta(){
        ElementoConta elemento = new ElementoConta();
        ultimo = elemento;
        primeiro = elemento;
    }

    public void inserir(Conta novaConta){
        ElementoConta novo = new ElementoConta();
        novo.setDado(novaConta);
        this.ultimo.setProximo(novo);
        novo.setAnterior(ultimo);
        this.ultimo = novo;
    }

    public Conta retirar(int numConta){
        ElementoConta aux = primeiro;
        while(aux.getProximo() != null){
            aux = aux.getProximo();
            if(aux == ultimo && aux.getDado().getNumeroConta() == numConta){
                aux.getAnterior().setProximo(null);
                ultimo = aux.getAnterior();
                aux.setAnterior(null);
                return aux.getDado();        
            }else if(aux.getDado().getNumeroConta() == numConta){
                aux.getAnterior().setProximo(aux.getProximo());
                aux.getProximo().setAnterior(aux.getAnterior());
                return aux.getDado();
            }              
        }

        return null;
    }

    public Conta consultar(int numConta) throws NotFoundAccountException{
        ElementoConta aux = primeiro;
        boolean achou = false;

        while(aux.getProximo() != null){
            aux = aux.getProximo();
            if(aux.getDado() != null){
                if(aux.getDado().getNumeroConta() == numConta){
                    achou = true;
                    return aux.getDado();
                }
            }
        }

        if(!achou)
            throw new NotFoundAccountException();

        return null;
    }

    public void listar(){
        ElementoConta aux = primeiro;
        while(aux.getProximo() != null){
            Conta conta = aux.getProximo().getDado();
            
            System.out.println("Numero conta: " + conta.getNumeroConta());
            System.out.println("Cpf: " + conta.getCpf());
            System.out.println("Saldo: " + conta.getSaldo() + "\n");

            aux = aux.getProximo();
        }
    }

    public void listarComSaldoTotal(){
        ElementoConta aux = primeiro;
        double saldoTotal = 0;
        while(aux.getProximo() != null){
            Conta conta = aux.getProximo().getDado();
            
            System.out.println("Numero conta: " + conta.getNumeroConta());
            System.out.println("Cpf: " + conta.getCpf());
            System.out.println("Saldo: " + conta.getSaldo() + "\n");
            saldoTotal += conta.getSaldo();

            aux = aux.getProximo();
        }
        System.out.println("SALDO TOTAL: R$" + saldoTotal);
    }

    public void salvarLista(String path, ListaConta contas, int contador){
        ElementoConta aux = primeiro;
        try(FileWriter write = new FileWriter(path)){
            write.write(contador + "\n");
            while(aux.getProximo() != null){
                Conta conta = aux.getProximo().getDado();
                if(conta != null)
                    write.write(conta.getNumeroConta() + ";" + conta.getCpf() + ";" + conta.getSaldo() + "\n");
                aux = aux.getProximo();
            }         
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo informado não encontrado!");
        }catch(Exception e2){
            e2.printStackTrace();
        }
    }

    public ListaConta buscaContasCliente(String cpf){
        ElementoConta aux = primeiro;

        ListaConta contasDoCliente = new ListaConta();

        while(aux.getProximo() != null){          
            if(aux.getProximo().getDado().getCpf().equals(cpf))
                contasDoCliente.inserir(aux.getProximo().getDado());

            aux = aux.getProximo();
        }

        return contasDoCliente;
    }


    public void relacionaOperacoes(ListaConta contas,Operacao[] operacoes){
        ElementoConta auxConta = primeiro;
        
        while(auxConta.getProximo() != null){
            for(var operacao: operacoes){
                if(operacao.getNumConta() == auxConta.getProximo().getDado().getNumeroConta())
                    auxConta.getProximo().getDado().getOperacoes().inserir(operacao);
            }
            auxConta = auxConta.getProximo();
        }
    }

    public void sacar(String cpf, int numConta, double valor) throws NotFoundAccountException{
        ElementoConta aux = primeiro;
        boolean achou = false;

        while(aux.getProximo() != null){
            Conta conta = aux.getProximo().getDado();
            if(conta.getNumeroConta() == numConta 
            && conta.getCpf().equals(cpf)){
                achou = true;
                aux.getProximo().getDado().sacar(valor);
                Operacao novaOperacao = new Operacao(conta.getNumeroConta(), 1, valor,geraData() ,true);
                conta.getOperacoes().inserir(novaOperacao);
            }                         
            aux = aux.getProximo();
        }
        if(!achou)
            throw new NotFoundAccountException();
    }

    public void depositar(String cpf, int numConta, double valor) throws NotFoundAccountException{
        ElementoConta aux = primeiro;
        boolean achou = false;

        while(aux.getProximo() != null){       
            Conta conta = aux.getProximo().getDado();
            if(conta.getNumeroConta() == numConta
            && conta.getCpf().equals(cpf)){
                achou = true;
                aux.getProximo().getDado().depositar(valor);                
                Operacao novaOperacao = new Operacao(conta.getNumeroConta(), 0, valor,geraData() ,true);
                conta.getOperacoes().inserir(novaOperacao);
            }
            aux = aux.getProximo();
        }
        if(!achou)
            throw new NotFoundAccountException();
    }
    
    private Data geraData(){
        LocalDate dataJava = LocalDate.now();

        int dia = dataJava.getDayOfMonth();
        int mes = dataJava.getMonthValue();
        int ano = dataJava.getYear();

        Data data = new Data(dia, mes, ano);
        return data;
    }


    public Conta getContaMaiorSaldo(){
        ElementoConta auxConta = primeiro;
        double maior = 0;
        Conta aux = null;
        
        while(auxConta.getProximo() != null){
            if(auxConta.getProximo().getDado() != null){
                if(auxConta.getProximo().getDado().getSaldo() > maior){
                    maior = auxConta.getProximo().getDado().getSaldo();
                    aux = auxConta.getProximo().getDado();
                }
            }
            auxConta = auxConta.getProximo();
        }
        return aux;
    }

    public void verExtrato(int numConta){
        ElementoConta auxConta = primeiro;
        
        while(auxConta.getProximo() != null){
            if(auxConta.getProximo().getDado().getNumeroConta() == numConta){
                auxConta.getProximo().getDado().getOperacoes().extrato(numConta);
                System.out.println("Conta: " + auxConta.getProximo().getDado().getNumeroConta());
                System.out.println("Saldo atual: " + auxConta.getProximo().getDado().getSaldo());
                System.out.println("-----------------------"); 
            }
            auxConta = auxConta.getProximo();
        }
    }

    public double somaSaldoDasContas(){
        double saldoTotal = 0;
        ElementoConta auxConta = primeiro;
        
        while(auxConta.getProximo() != null){
            if(auxConta.getProximo().getDado() != null){
                saldoTotal += auxConta.getProximo().getDado().getSaldo();
            }
            auxConta = auxConta.getProximo();
        }

        return saldoTotal;
    }

    public void executarOperacoes(ListaConta contas){
        ElementoConta auxConta = primeiro;
        
        while(auxConta.getProximo() != null){
            Conta conta = auxConta.getProximo().getDado();

            Operacao op = auxConta.getProximo().getDado().getOperacoes().retirar();
            
            while(op != null && !op.getRealizada()){
                if(conta != null){
                    if(op.getCodOperacao() == 0)
                        conta.depositar(op.getValor());
                    if(op.getCodOperacao() == 1)
                        conta.sacar(op.getValor());
                }
                op = auxConta.getProximo().getDado().getOperacoes().retirar();
            }

            auxConta = auxConta.getProximo(); 
        }
    }
}
