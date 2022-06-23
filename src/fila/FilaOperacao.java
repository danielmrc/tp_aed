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
        
        if(auxRetirada != null){
            if(primeiro.getProximo().getProximo() != null){
                primeiro.setProximo(primeiro.getProximo().getProximo());
                this.ultimo = primeiro.getProximo();
                auxRetirada.setProximo(null);
                return auxRetirada.getDado();
            }else{ 
                primeiro.setProximo(null);
                this.ultimo = primeiro;
                return auxRetirada.getDado();
            }
        }
        return null;
    }

    public boolean ehDessaConta(int numConta){
        ElementoOperacao aux = primeiro;

        if(aux.getProximo().getDado().getNumConta() == numConta)
            return true;
            else 
                return false;
    }

    public void extrato(int numConta){
        ElementoOperacao auxOperacao = primeiro;
        
        System.out.println("------- Extrato -------");
        while(auxOperacao.getProximo() != null){
            if(auxOperacao.getProximo().getDado().getNumConta() == numConta){
                switch(auxOperacao.getProximo().getDado().getCodOperacao()){
                    case 1: System.out.println("Saque");
                    break;
                    case 0: System.out.println("Depósito");
                    break;
                    default: System.out.println("Código de operação desconhecido!!");
                    break;
                }
                System.out.println("Valor: " + auxOperacao.getProximo().getDado().getValor());
                System.out.println("Data: " + auxOperacao.getProximo().getDado().getData().getData() + "\n\n");
            }
        System.out.println("-----------------------");

        auxOperacao = auxOperacao.getProximo();
        }
    }


}