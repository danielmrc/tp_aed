package banco;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

import lista.ListaConta;
import data.Data;
import exception.NotFoundAccountException;

public class ControleConta {

    private Random sorteio = new Random(System.currentTimeMillis());

    private String arquivoNome = null;

    private int cont = 0;

    public int getCont(){
        return this.cont;
    }

    public ListaConta chargeContas(String path){
        System.out.println("Aguarde, estamos carregando o arquivo de contas...");
        ListaConta contas = new ListaConta();

        String line;
        File file = new File(path);
        String[] breakString = new String[3];

        arquivoNome = path;

        try(Scanner scan = new Scanner(file)){
            //numeroContas = Integer.parseInt(scan.next());
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    contas.inserir(new Conta(
                        Integer.parseInt(breakString[0]),
                        breakString[1],
                        Double.parseDouble(breakString[2])
                    ));
                    
                    cont++;
                }
            }            
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e2){
            e2.printStackTrace();
        }

        System.out.println("Arquivo de contas carregado!!");
        return contas;
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

    public void executarOperacoes(ListaConta contas){
        contas.executarOperacoes(contas);
    }

    public Conta consulta(ListaConta contas, int num) throws NotFoundAccountException{
        Conta conta = null;

        conta = contas.consultar(num);

        return conta;
    }


    public Operacao[] chargeOperacao(String path){
        System.out.println("Aguarde, estamos carregando o arquivo de operações...");
        Operacao[] operacoes = null;

        int contador = 0;

        Data data;

        String line;
        File file = new File(path);
        String[] breakString = new String[4];
        String[] breakData = new String[3];
        int numeroOperacoes;

        arquivoNome = path;

        try(Scanner scan = new Scanner(file)){
            numeroOperacoes = Integer.parseInt(scan.next());
            operacoes = new Operacao[numeroOperacoes];
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    breakData = breakString[3].split("/");
                    data = new Data(Integer.parseInt(breakData[0]), Integer.parseInt(breakData[1]), Integer.parseInt(breakData[2]));
                    operacoes[contador] = new Operacao(
                        Integer.parseInt(breakString[0]),
                        Integer.parseInt(breakString[1]),
                        Double.parseDouble(breakString[2]),
                        data                        
                    );
                    
                    contador++;
                }
            }            
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e2){
            e2.printStackTrace();
        }
        System.out.println("Arquivo de operações carregado!!");
        return operacoes;
    }

    
}
