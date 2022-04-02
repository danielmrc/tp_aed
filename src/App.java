import java.util.Scanner;

public class App {

    public static LoadingAccounts controller = new LoadingAccounts();

    public static Scanner scan = new Scanner(System.in);

    public static Conta[] contas;
    public static void main(String[] args) throws Exception {
        //professor, quando estiver testando se usar sempre que iniciar, vai sobrescrever as contas criadas manualmente e salvas no fim do fluxo
        System.out.println("Deseja gerar arquivo inicial com 100 contas aleatórias? (S - para sim, N - para nao)");
        if(scan.nextLine().equals("S")){
            GeraArquivo fazArquivo = new GeraArquivo();
            fazArquivo.gerar("teste.txt");
        }
        Menu();       
    }


    public static void Menu(){
    
        String nomeArquivo = null;
        int op = 0;

        do{
            System.out.println("-------- Escolha alguma das opções abaixo ---------\n" 
            + "1 - Carregar dados \n"
            + "2 - Gerar relatório \n"
            + "3 - Criar uma conta \n"
            + "4 - Exibir de maneira ordenada pelo número da conta \n"
            + "5 - Sair");
            op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1:
                    System.out.println("Informe o nome do arquivo: ");
                    nomeArquivo = scan.nextLine();
                    contas = controller.charge(nomeArquivo);
                break;
                case 2:
                    controller.gerarRelatorio(contas);
                break;
                case 3:                    
                    criarConta();
                break;
                case 4:
                    exibeOrdenado(contas);
                break;
                case 5:
                    op = 0;
                break;
                default:
                    System.out.println("Informe uma opção válida!!");
                break;      
            }
        }while(op != 0);
        scan.close();
        controller.salvar(contas);
    }


    static void criarConta(){
        int conta;
        String cp;
        double sal;
                
        conta = controller.gerarNumeroConta(100, 3.0)[0];
        System.out.println("Informe seu Cpf: ");
        cp = scan.nextLine();
        System.out.println("Deseja depositar durante a criação? se não digite zero, se sim informe o valor: ");
        sal = Double.parseDouble(scan.nextLine());

        controller.criarConta(contas, conta, cp, sal);
    }


    public static void exibeOrdenado(Conta[] contas){
        for(var conta: controller.quickSort(contas, 0, Integer.MIN_VALUE)){
            if(conta != null){
                    System.out.println("Numero conta: " + conta.getNumeroConta());
                    System.out.println("Cpf: " + conta.getCpf());
                    System.out.println("Saldo: " + conta.getSaldo() + "\n");
            }
        }
    }

    
}
