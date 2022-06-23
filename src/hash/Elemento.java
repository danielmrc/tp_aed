package hash;

import fila.FilaOperacao;

public class Elemento {
    private FilaOperacao value;
    private boolean existe;

    public Elemento(){
        this.value = null;
        this.existe = false;
    }

    public Elemento(FilaOperacao value){
        this.value = value;
    }

    public Elemento(FilaOperacao value, boolean existe){
        this.value = value;
        this.existe = existe;
    }

    public FilaOperacao getValue(){
        return this.value;
    }

    public void setValue(FilaOperacao value){
        this.value = value;
    }

    public boolean getExiste(){
        return this.existe;
    }

    public void setExiste(boolean existe){
        this.existe = existe;
    }
    
}
