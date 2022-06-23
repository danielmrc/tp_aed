package banco;
import java.util.Random;

import lista.ListaConta;
import exception.NotFoundAccountException;

public class ControleConta {

    private Random sorteio = new Random(System.currentTimeMillis());

    private String arquivoNome = null;

    private int cont = 0;

    public int getCont(){
        return this.cont;
    }

    public void gerarRelatorio(ListaConta contas){
        contas.listar();
        System.out.println();
    }


    public void criarConta(ListaConta contas, Conta nova){     
        contas.inserir(nova);
    }


    public void salvar(ListaConta contas){
        contas.salvarLista(arquivoNome, contas, cont);
    }


    public int[] gerarNumeroConta(int tamanho, double bagunca){
        int[] numeroContas = new int[tamanho];

        for(int i = 0; i < numeroContas.length; i++){
            numeroContas[i] = i +1;
        }

        int vezes = (int)(bagunca * tamanho);

        for(int i = 0; i < vezes; i++){
            int pos1 = sorteio.nextInt(tamanho);
            int pos2 = sorteio.nextInt(tamanho);
            int aux = numeroContas[pos1];
            numeroContas[pos1] = numeroContas[pos2];
            numeroContas[pos2] = aux;
        }

        return numeroContas;
    }



    public Conta[] quickSort(Conta[]contas, int inicio, int fim){
        if(fim == Integer.MIN_VALUE)
            fim = cont -1;

        int part;
        if(inicio < fim){
            part = particao(contas, inicio, fim);
            quickSort(contas, inicio, part -1);
            quickSort(contas, part +1, fim);
        }else{
            return contas;
        }

        return contas;
    }

    public int particao(Conta[]contas, int inicio, int fim){
        int pivot = contas[fim].getNumeroConta();
        int part = inicio -1;
        Conta aux;

        for (int i = inicio; i < cont -1; i++) {
            if(contas[i].getNumeroConta() < pivot){
                part = part + 1;
                aux = contas[part];
                contas[part] = contas[i];
                contas[i] = aux;
            }
        }

        part++;
        aux = contas[fim];
        contas[fim] = contas[part];
        contas[part] = aux;

        return part;
    }

    public Conta consulta(ListaConta contas, int num) throws NotFoundAccountException{
        Conta conta = null;

        conta = contas.consultar(num);

        return conta;
    }
    
}
