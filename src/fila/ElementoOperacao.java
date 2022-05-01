package fila;

import banco.Operacao;

public class ElementoOperacao {
    private ElementoOperacao proximo;
    private Operacao dado;

    public ElementoOperacao(){
        this.proximo = null;
        this.dado = null;
    }

    public ElementoOperacao(ElementoOperacao proximo, Operacao dado){
        this.proximo = proximo;
        this.dado = dado;
    }

    public void setProximo(ElementoOperacao proximo){
        this.proximo = proximo;
    }

    public ElementoOperacao getProximo(){
        return this.proximo;
    }

    public void setDado(Operacao dado){
        this.dado = dado;
    }

    public Operacao getDado(){
        return this.dado;
    }
    
    
}
