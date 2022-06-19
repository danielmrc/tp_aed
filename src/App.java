import java.util.Scanner;

import banco.Conta;
import banco.ControleConta;
import cliente.ControleCliente;
import lista.ListaConta;
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
            + "7 - Localizar cliente \n"
            + "8 - Sair");
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
                    localizaCliente();
                break;
                case 8:
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

    static void localizaCliente(){
        System.out.println("Informe o cpf do cliente: ");
        String cpf = scan.nextLine();

        if(verificaCliente(cpf))
            menuCliente(cpf);
            else
                System.out.println("Cpf do cliente não encontrado na base de dados");
    }

    public static void menuCliente(String cpf){
        int op = Integer.MAX_VALUE;
        do{
            System.out.println("--------------------------");
            System.out.println("1 - Verificar extrato de uma conta \n"
            + "2 - Incluir uma nova conta \n"
            + "3 - Realizar Saque \n"
            + "4 - Realizar Depósito \n"
            + "5 - Extrato de sua posição financeira \n"
            + "6 - Sair");

            op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1:
                    System.out.println("Informe o número da conta: ");
                    verificaExtratoCliente(Integer.parseInt(scan.nextLine()));
                break;
                case 2: 
                    System.out.println("Se desejar depositar na criação da conta digite"
                    + " a seguir, caso contrário digite 0");
                    criarConta(cpf, Double.parseDouble(scan.nextLine()));
                break;
                case 3:
                    System.out.println("Informe o numero da conta:");
                    int numConta = Integer.parseInt(scan.nextLine());
                    System.out.println("Informe o valor a ser sacado:");
                    double valor = Double.parseDouble(scan.nextLine());
                    realizarSaque(cpf, numConta, valor);
                break;
                case 4:
                    System.out.println("Informe o numero da conta:");
                    numConta = Integer.parseInt(scan.nextLine());
                    System.out.println("Informe o valor a ser sacado:");
                    valor = Double.parseDouble(scan.nextLine());
                    realizarDeposito(cpf, numConta, valor);
                break;
                case 6:
                    op = 0;
                break;
            }
        }while(op != 0);
    }

    public static void realizarDeposito(String cpf, int numConta, double valor){
        contas.depositar(cpf, numConta, valor);
    }

    public static void realizarSaque(String cpf, int numConta, double valor){
        contas.sacar(cpf, numConta, valor);
    }

    public static void verificaExtratoCliente(int numConta){
        contas.verExtrato(numConta);
    }

    static boolean verificaCliente(String cpf){
        for(var cliente: clientes){
            if(cliente != null)
                if(cliente.getCpf().equals(cpf))
                    return true;
        }
        return false;
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

        Conta conta = controllerContas.consulta(contas, num);
        
        if(conta != null){
            System.out.println("Numero conta: " + conta.getNumeroConta());
            System.out.println("Cpf: " + conta.getCpf());
            System.out.println("Saldo: " + conta.getSaldo() + "\n");
        }else{
            System.out.println("Conta nao encontrada!!");
        }
        
    }

    public static void criarConta(String cpf, double saldo){
        Conta novaConta = new Conta(controllerContas.gerarNumeroConta(100, 3.0)[0], cpf, saldo);
        controllerContas.criarConta(contas, novaConta);
        controllerCliente.relacionaContas(contas, clientes);
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
        controllerCliente.relacionaContas(contas, clientes);
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
