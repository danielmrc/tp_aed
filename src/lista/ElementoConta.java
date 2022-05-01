package lista;

import banco.Conta;

public class ElementoConta{
    private ElementoConta proximo;
    private ElementoConta anterior;
    private Conta dado;


    public ElementoConta(){
        this.anterior = null;
        this.proximo = null;
        this.dado = null;
    }

    public ElementoConta(ElementoConta proximo, ElementoConta anterior, Conta dado){
        this.proximo = proximo;
        this.anterior = anterior;
        this.dado = dado;
    }

    public void setProximo(ElementoConta proximo){
        this.proximo = proximo;
    }

    public void setAnterior(ElementoConta anterior){
        this.anterior = anterior;
    }

    public ElementoConta getProximo(){
        return this.proximo;
    }

    public ElementoConta getAnterior(){
        return this.anterior;
    }

    public void setDado(Conta dado){
        this.dado = dado;
    }

    public Conta getDado(){
        return this.dado;
    }
    
}
