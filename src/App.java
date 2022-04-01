import java.util.Scanner;

public class App {

    public static Conta[] contas;
    public static void main(String[] args) throws Exception {
        //GeraArquivo fazArquivo = new GeraArquivo();
        //fazArquivo.gerar("teste.txt");

        Menu();       
    }


    public static void Menu(){
        Scanner scan = new Scanner(System.in);
        LoadingAccounts controller = new LoadingAccounts();

        String nomeArquivo = null;
        int op = 0;

        do{
            System.out.println("-------- Escolha alguma das opções abaixo ---------\n" 
            + "1 - Carregar dados \n"
            + "2 - Gerar relatório \n"
            + "3 - Criar uma conta \n"
            + "4 - Sair");
            op = scan.nextInt();

            switch(op){
                case 1:
                    System.out.println("Informe o nome do arquivo: ");
                    nomeArquivo = scan.next();
                    contas = controller.charge(nomeArquivo);
                break;
                case 2:
                    controller.gerarRelatorio(contas);
                break;
                case 3:
                    controller.criarConta(contas);
                break;
                default:
                    controller.salvar(contas);
                break;        
            }
        }while(op != 4);

        scan.close();
    }
}
