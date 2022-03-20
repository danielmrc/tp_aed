import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

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
            + "3 - Criar uma conta"
            + "4 - Sair");
            op = scan.nextInt();

            switch(op){
                case 1:
                    System.out.println("Informe o nome do arquivo: ");
                    nomeArquivo = scan.nextLine();
                    controller.charge(nomeArquivo);
                break;
                case 2:
                    controller.gerarRelatorio();
                break;
                case 3:
                    controller.criarConta();

            }
        }while(op != 3);

        scan.close();
    }
}
