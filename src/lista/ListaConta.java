package lista;

import banco.Conta;

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
            if(aux.getDado().getNumeroConta() == numConta)
                return aux.getDado();
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


}
