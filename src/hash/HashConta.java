package hash;

import lista.ListaConta;
import banco.Conta;

public class HashConta {

    private ElementoConta[] contas;


    public HashConta(int tamanho){
        this.contas = new ElementoConta[tamanho];
        for(int i = 0; i < tamanho; i++){
            contas[i] = new ElementoConta();
        }
    }

 
    public int codigo(String cpf){
        int cod = 0;
        String aux = "";
       
        for(int i = 0; i < cpf.length(); i ++){
            int num = Character.getNumericValue(cpf.charAt(i));
            if(num > 0)
                aux += Integer.toString(num);
        }

        cod = Integer.parseInt(aux);

        return cod * 11;
    }


    public int mapeamento(String cpf){
        return codigo(cpf) % contas.length;
    }

    public void inserir(String cpf, Conta value){
        
        int pos = mapeamento(cpf);
        for(int i = pos; i < contas.length; i++){
            if(contas[i].getValue() == null && !contas[i].getExiste()){
                ListaConta novaLista = new ListaConta();
                contas[i].setValue(novaLista);
                contas[i].getValue().inserir(value);
                contas[i].setExiste(true);
                break;
            }else if(contas[i].getValue() != null && contas[i].getExiste() && contas[i].getValue().ehDesseCliente(cpf)){
                contas[i].getValue().inserir(value);
                break;
            }
        }
        
    }

    public ListaConta retirar(String cpf){
        int pos = mapeamento(cpf);

        for(int i = pos; i < contas.length; i++){
            if(contas[i].getValue() == null && !contas[i].getExiste())
                break;              
            if(contas[i].getValue().ehDesseCliente(cpf)){
                contas[i].setExiste(false);
                return contas[i].getValue();
            }
        }
        return null;
    }
    
}
