package hash;

import lista.ListaConta;

public class ElementoConta {
    private ListaConta value;
    private boolean existe;

    public ElementoConta(){
        this.value = null;
        this.existe = false;
    }

    public ElementoConta(ListaConta value){
        this.value = value;
    }

    public ElementoConta(ListaConta value, boolean existe){
        this.value = value;
        this.existe = existe;
    }

    public ListaConta getValue(){
        return this.value;
    }

    public void setValue(ListaConta value){
        this.value = value;
    }

    public boolean getExiste(){
        return this.existe;
    }

    public void setExiste(boolean existe){
        this.existe = existe;
    }
    
    
}
