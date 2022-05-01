package lista;

public class Elemento {

    private Elemento proximo;
    private Elemento anterior;
    private Object dado;

    public Elemento(){
        this.proximo = null;
        this.anterior = null;
        this.dado = null;
    }

    public void setProximo(Elemento proximo){
        this.proximo = proximo;
    }

    public void setAnterior(Elemento anterior){
        this.anterior = anterior;
    }

    public void setDado(Object dado){
        this.dado = dado;
    }

    public Elemento getProximo(){
        return this.proximo;
    }

    public Elemento getAnterior(){
        return this.anterior;
    }

    public Object getDado(){
        return this.dado;
    }
}
