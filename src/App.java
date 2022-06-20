import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import banco.Conta;
import banco.ControleConta;
import cliente.ControleCliente;
import lista.ListaConta;
import cliente.Cliente;
import banco.Operacao;
import exception.NotFoundAccountException;
//import fila.FilaOperacao;
import banco.Admin;


public class App {

    public static ControleConta controllerContas = new ControleConta();

    public static ControleCliente controllerCliente = new ControleCliente();

    public static Scanner scan = new Scanner(System.in);

    public static Cliente[] clientes;

    public static Admin[] admins;

    public static ListaConta contas;

    public static int contCliente = 0;

    public static int contOperacoes = 0;

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
    
        String[] nomeArquivo = new String[4];
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
            + "8 - Funções de administrador \n"
            + "9 - Sair");
            op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1:
                    System.out.println("Informe o nome dos arquivos no formato: contas.txt;clientes.txt;operacoes.txt;admin.txt");
                    nomeArquivo = scan.nextLine().split(";");
                    contas = controllerContas.chargeContas(nomeArquivo[0]);
                    clientes = controllerCliente.chargeClientes(nomeArquivo[1]);
                    operacoes = controllerContas.chargeOperacao(nomeArquivo[2]);
                    admins = carregarAdmins(nomeArquivo[3]);
                    controllerCliente.relacionaContas(contas, clientes);
                    contas.relacionaOperacoes(contas, operacoes);
                break;
                case 2:
                    //controllerContas.gerarRelatorio(contas);
                    listarContas();
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
                    menuAdmin();
                break;
                case 9:
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


    static void menuAdmin(){
        int op = 0;
        System.out.println("Informe o número de cpf: ");
        String cpf = scan.nextLine();
        if(verificaAdmin(cpf)){
            do{
                System.out.println("--------------------------");
                System.out.println("1 - Conta com maior saldo no momento \n"
                + "2 - Saldo médio dos clientes\n"
                + "3 - Dez clientes com maior saldo total \n"
                + "4 - Sair");

                op = Integer.parseInt(scan.nextLine());

                switch(op){
                    case 1:                       
                        controllerCliente.maiorSaldo(clientes);
                    break;
                    case 2: 
                        controllerCliente.saldoMedio(clientes);
                    break;
                    case 3:
                        controllerCliente.clientesMaioresSaldos(clientes);
                    break;
                    case 4:
                        op = 0;
                    break;
                    default:
                        System.out.println("Informe uma opção válida!!");
                    break;
                }
            }while(op != 0);
        }
    }

    static void listarContas(){
        for(var cliente:clientes){
            if(cliente != null)
                cliente.getContas().listar();
        }
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
        int op = 0;
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
                    verificaExtratoCliente(cpf, Integer.parseInt(scan.nextLine()));
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
                    System.out.println("Informe o valor a ser depositado:");
                    valor = Double.parseDouble(scan.nextLine());
                    realizarDeposito(cpf, numConta, valor);
                break;
                case 5:
                    consultarContas(cpf);
                break;
                case 6:
                    op = 0;
                break;
            }
        }while(op != 0);
    }

    public static void realizarDeposito(String cpf, int numConta, double valor){
        controllerCliente.realizarDeposito(cpf, numConta, valor, clientes);
    }

    public static void realizarSaque(String cpf, int numConta, double valor){
        controllerCliente.realizarSaque(cpf, numConta, valor, clientes);
    }

    public static void verificaExtratoCliente(String cpf, int numConta){
        for(var cliente: clientes){
            if(cliente != null){
                if(cliente.getCpf().equals(cpf)){
                       cliente.getContas().verExtrato(numConta);                     
                }
            }
        }
    }

    static boolean verificaCliente(String cpf){
        for(var cliente: clientes){
            if(cliente != null)
                if(cliente.getCpf().equals(cpf))
                    return true;
        }
        return false;
    }

    static boolean verificaAdmin(String cpf){
        for(var admin: admins){
            if(admin != null)
                if(admin.getCpf().equals(cpf))
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

    static Admin[] carregarAdmins(String arquivo){
        Admin[] adminsCarregados = new Admin[100];
        int cont = 0;

        File arquivoAdmin = new File(arquivo);
        try(Scanner leitor = new Scanner(arquivoAdmin)){
            while(leitor.hasNextLine()){
                String linha = leitor.nextLine();
                String[] linhaQuebrada = linha.split(";");
                Admin admin = new Admin(linhaQuebrada[0], linhaQuebrada[1]);
                adminsCarregados[cont] = admin;
                cont++;
            }
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo não encontrado!" + e1.getMessage());
        }catch(Exception e2){
            e2.printStackTrace();
        }

        return adminsCarregados;
    }

    static void consultarContas(String cpf){
        for(var cliente: clientes){
            if(cliente != null)
                if(cliente.getCpf().equals(cpf))
                    cliente.getContas().listarComSaldoTotal();
        }
    }

    static void extrato(){
        System.out.println("Numero da conta: ");
        int numConta = Integer.parseInt(scan.nextLine());
        System.out.println("Informe o cpf do titular: ");
        String cpf = scan.nextLine();

        for(var cliente: clientes){
            if(cliente != null){
                if(cliente.getCpf().equals(cpf)){
                       cliente.getContas().verExtrato(numConta);                     
                }
            }
        }
        contas.verExtrato(numConta);
    }

    static void busca(){
        int num;
        
        System.out.println("Informe o numero da conta: ");
        num = Integer.parseInt(scan.nextLine());
        System.out.println("Informe o cpf do titular: ");
        String cpf = scan.nextLine();

        try{
            for(var cliente: clientes){
                if(cliente != null){
                    if(cliente.getCpf().equals(cpf)){
                        Conta conta = cliente.getContas().consultar(num);
                        System.out.println("Numero conta: " + conta.getNumeroConta());
                        System.out.println("Cpf: " + conta.getCpf());
                        System.out.println("Saldo: " + conta.getSaldo() + "\n");
                    }
                }
            }
        }catch(NotFoundAccountException e1){
            System.out.println("Conta não encontrada!! " + e1.getMessage());
        }

       /* try{
            Conta conta = controllerContas.consulta(contas, num);
            
            System.out.println("Numero conta: " + conta.getNumeroConta());
            System.out.println("Cpf: " + conta.getCpf());
            System.out.println("Saldo: " + conta.getSaldo() + "\n");
        }catch(NotFoundAccountException e1){
            System.out.println(e1.getMessage());
        }*/
    }

    public static void criarConta(String cpf, double saldo){
        int numConta = controllerContas.gerarNumeroConta(100, 3.0)[0];
        Conta novaConta = new Conta(numConta, cpf, saldo);
        try{
            for(var cliente: clientes){
                if(cliente != null){
                    if(cliente.getCpf().equals(cpf))
                        cliente.getContas().inserir(novaConta);
                }
            }
            //controllerCliente.relacionaContas(contas, clientes);
            System.out.println("Conta criada com sucesso!\n"
            + "Número da conta:" + numConta);
        }catch(Exception e1){
            System.out.println("Ocorreu uma falha ao criar a conta!" + e1.getMessage());
        }
    }


    static void criarConta(){
        int conta;
        String cp;
        double sal;
        boolean achou = false;
                
        conta = controllerContas.gerarNumeroConta(100, 3.0)[0];
        System.out.println("Informe seu Cpf: ");
        cp = scan.nextLine();
        System.out.println("Deseja depositar durante a criação? se não digite zero, se sim informe o valor: ");
        sal = Double.parseDouble(scan.nextLine());

        try{
            Conta novaConta = new Conta(conta, cp, sal);
            for(var cliente: clientes){
                if(cliente != null){
                    if(cliente.getCpf().equals(cp)){
                        cliente.getContas().inserir(novaConta);
                        achou = true;
                        System.out.println("Conta criada com sucesso!\n"
                        +"Número da conta:" + conta);
                    }
                }
                //controllerCliente.relacionaContas(contas, clientes);
            }

            if(!achou){          
                System.out.println("Não existe cliente cadastrado com esse cpf,"
                + " informe seu nome para criação da conta: ");
                try{
                    String nome = scan.nextLine();
                    Cliente novoCliente = new Cliente(cp, nome);
                    novoCliente.getContas().inserir(novaConta);
                    clientes[controllerCliente.getCont()] = novoCliente;
                    System.out.println("Conta criada com sucesso!\n"
                    +"Número da conta:" + conta);
                }catch(Exception e1){
                    System.out.println("Ocorreu um erro durante a criacao do cliente!" + e1.getMessage());
                }
            }              
        }catch(Exception e1){
            System.out.println("Ocorreu uma falha ao criar a conta!");
            e1.printStackTrace();
        }
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
