package lista;

import banco.Conta;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import fila.FilaOperacao;
import banco.Operacao;
import fila.ElementoOperacao;

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
            if(aux.getDado().getNumeroConta() == numConta){
                aux.getAnterior().setProximo(aux.getProximo());
                aux.getProximo().setAnterior(aux.getAnterior());
                return aux.getDado();
            }
            aux = aux.getProximo();    
        }

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

    public void verExtrato(int numConta){
        ElementoConta auxConta = primeiro;
        
        while(auxConta.getProximo() != null){
            if(auxConta.getProximo().getDado().getNumeroConta() == numConta)
                auxConta.getProximo().getDado().getOperacoes().extrato(numConta);
            auxConta = auxConta.getProximo(); 
        }
    }

}
