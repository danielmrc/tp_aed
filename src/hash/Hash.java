package hash;

import banco.Operacao;
import fila.FilaOperacao;

public class Hash {

    private Elemento[] operacoes;


    public Hash(int tamanho){
        this.operacoes = new Elemento[tamanho];
        for(int i = 0; i < tamanho; i++){
            operacoes[i] = new Elemento();
        }
    }

 
    public int codigo(int conta){
        int cod = 0;
        
        cod = conta * 10;

        return cod;
    }


    public int mapeamento(int conta){
        return codigo(conta) % operacoes.length;
    }

    public void inserir(int conta, Operacao value){
        
        int pos = mapeamento(conta);
        for(int i = pos; i < operacoes.length; i++){
            if(operacoes[i].getValue() == null && !operacoes[i].getExiste()){
                FilaOperacao novaFila = new FilaOperacao();
                operacoes[i].setValue(novaFila);
                operacoes[i].getValue().inserir(value);
                operacoes[i].setExiste(true);
                break;
            }else if(operacoes[i].getValue() != null && operacoes[i].getExiste() && operacoes[i].getValue().ehDessaConta(conta)){
                operacoes[i].getValue().inserir(value);
                break;
            }
        }
        
    }

    public FilaOperacao retirar(int conta){
        int pos = mapeamento(conta);

        for(int i = pos; i < operacoes.length; i++){
            if(operacoes[i].getValue() == null && !operacoes[i].getExiste())
                break;              
            if(operacoes[i].getValue().ehDessaConta(conta)){
                operacoes[i].setExiste(false);
                return operacoes[i].getValue();
            }
        }
        return null;
    }
    

}
