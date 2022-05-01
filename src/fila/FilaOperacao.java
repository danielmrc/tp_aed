package fila;

import banco.Operacao;

public class FilaOperacao {
    ElementoOperacao primeiro;
    ElementoOperacao ultimo;

    public FilaOperacao(){
        ElementoOperacao elemento = new ElementoOperacao();
        ultimo = elemento;
        primeiro = elemento;
    }

    public void inserir(Operacao novaOperacao){
        ElementoOperacao novo = new ElementoOperacao();
        novo.setDado(novaOperacao);
        this.ultimo.setProximo(novo);
        this.ultimo = novo;
    }

    public Operacao retirar(){
        ElementoOperacao auxRetirada = primeiro.getProximo();
        primeiro.setProximo(primeiro.getProximo().getProximo());

        auxRetirada.setProximo(null);

        return auxRetirada.getDado();
    }

    public void extrato(int numConta){
        ElementoOperacao auxOperacao = primeiro;
        
        System.out.println("------- Extrato -------");
        while(auxOperacao.getProximo() != null){
            if(auxOperacao.getProximo().getDado().getNumConta() == numConta)
                if(auxOperacao.getProximo().getDado().getCodOperacao() == 1)
                    System.out.println("Saque");
                if(auxOperacao.getProximo().getDado().getCodOperacao() == 0)
                    System.out.println("Depósito");
                    else
                        System.out.println("Código de operação desconhecido!!");
                System.out.println("Numero da conta: " + auxOperacao.getProximo().getDado().getNumConta());
                System.out.println("Valor: " + auxOperacao.getProximo().getDado().getValor());
                System.out.println("Data: " + auxOperacao.getProximo().getDado().getData().getData() + "\n\n");
        }
        System.out.println("-----------------------");
    }


    
}
