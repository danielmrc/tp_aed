import java.util.Scanner;

import banco.Conta;
import banco.ControleConta;
import cliente.ControleCliente;
import lista.ListaConta;
import fila.FilaOperacao;
import cliente.Cliente;
import banco.Operacao;


public class App {

    public static ControleConta controllerContas = new ControleConta();

    public static ControleCliente controllerCliente = new ControleCliente();

    public static Scanner scan = new Scanner(System.in);

    public static Cliente[] clientes;

    public static ListaConta contas;

    public static Operacao[] operacoes;
    public static void main(String[] args) throws Exception {
        //professor, quando estiver testando se usar sempre que iniciar, vai sobrescrever as contas criadas manualmente e salvas no fim do fluxo
        //System.out.println("Deseja gerar arquivo inicial com 100 contas aleatórias? (S - para sim, N - para nao)");
        //if(scan.nextLine().equals("S")){
          //  GeraArquivo fazArquivo = new GeraArquivo();
            //fazArquivo.gerar("teste.txt");
        //}
        Menu();       
    }


    public static void Menu(){
    
        String[] nomeArquivo = new String[3];
        int op = 0;

        do{
            System.out.println("\n-------- Escolha alguma das opções abaixo ---------\n" 
            + "1 - Carregar dados \n"
            + "2 - Listar Contas \n"
            + "3 - Criar uma conta \n"
            + "4 - Consultar contas de um cliente \n"
            + "5 - Buscar conta pelo número \n"
            + "6 - Ver extrato de uma conta \n"
            + "7 - Sair");
            op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1:
                    System.out.println("Informe o nome dos arquivos no formato: contas.txt;clientes.txt;operacoes.txt");
                    nomeArquivo = scan.nextLine().split(";");
                    contas = controllerContas.chargeContas(nomeArquivo[0]);
                    clientes = controllerCliente.chargeClientes(nomeArquivo[1]);
                    operacoes = controllerContas.chargeOperacao(nomeArquivo[2]);
                    controllerCliente.relacionaContas(contas, clientes);
                    contas.relacionaOperacoes(contas, operacoes);
                break;
                case 2:
                    controllerContas.gerarRelatorio(contas);
                break;
                case 3:                    
                    criarConta();
                break;
                case 4:
                    consultarContas();
                break;
                case 5:
                    busca();
                break;
                case 6:
                    extrato();
                break;
                case 7:
                    op = 0;
                break;
                default:
                    System.out.println("Informe uma opção válida!!");
                break;      
            }
        }while(op != 0);
        scan.close();
        //trollerContas.salvar(contas);
    }


    static void consultarContas(){
        String cpf;

        System.out.println("Informe o cpf do titular: ");
        cpf = scan.nextLine();

        for(var cliente: clientes){
            if(cliente != null)
                if(cliente.getCpf().equals(cpf))
                    cliente.getContas().listar();
        }
    }

    static void extrato(){
        System.out.println("Numero da conta: ");
        int numConta = Integer.parseInt(scan.nextLine());

        contas.verExtrato(numConta);
    }

    static void busca(){
        int num;
        
        System.out.println("Informe o numero da conta: ");
        num = Integer.parseInt(scan.nextLine());

        Conta conta = controllerContas.buscaConta(contas, num);
        
        if(conta != null){
            System.out.println("Numero conta: " + conta.getNumeroConta());
            System.out.println("Cpf: " + conta.getCpf());
            System.out.println("Saldo: " + conta.getSaldo() + "\n");
        }else{
            System.out.println("Conta nao encontrada!!");
        }
        
    }


    static void criarConta(){
        int conta;
        String cp;
        double sal;
                
        conta = controllerContas.gerarNumeroConta(100, 3.0)[0];
        System.out.println("Informe seu Cpf: ");
        cp = scan.nextLine();
        System.out.println("Deseja depositar durante a criação? se não digite zero, se sim informe o valor: ");
        sal = Double.parseDouble(scan.nextLine());

        Conta novaConta = new Conta(conta, cp, sal);
        controllerContas.criarConta(contas, novaConta);
    }


    public static void exibeOrdenado(Conta[] contas){
        for(var conta: controllerContas.quickSort(contas, 0, Integer.MIN_VALUE)){
            if(conta != null){
                    System.out.println("Numero conta: " + conta.getNumeroConta());
                    System.out.println("Cpf: " + conta.getCpf());
                    System.out.println("Saldo: " + conta.getSaldo() + "\n");
            }
        }
    }


}
