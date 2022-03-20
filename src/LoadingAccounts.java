import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class LoadingAccounts {

    private Conta[] contas = null;

    private String arquivoNome = null;

    private int cont = 0;


    public Conta[] getConta(){
        return this.contas;
    }

    public void charge(String path){
        File file = new File(path);
        contas = new Conta[100];
        String[] breakString = new String[3];

        arquivoNome = path;

        try(Scanner scan = new Scanner(file)){
            while(scan.hasNextLine()){
                breakString = scan.nextLine().split(";");
                contas[cont] = new Conta(
                    Integer.parseInt(breakString[0]),
                    breakString[1],
                    Double.parseDouble(breakString[2])
                );
                
                cont++;
            }            
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e){
            System.out.println("Ops, Algo deu errado no carregamento das classes através dos arquivos!");
        }
    }


    public void gerarRelatorio(){
    
        for(Conta conta: contas){
            if(conta != null){
                System.out.println("Numero conta: " + conta.getNumeroConta());
                System.out.println("Cpf: " + conta.getCpf());
                System.out.println("Saldo: " + conta.getSaldo() + "\n");
            }            
        }
    System.out.println();

    }


    public void criarConta(){
        int conta;
        String cp;
        double sal;

        Scanner scan = new Scanner(System.in);

        System.out.println("Informe o número da conta: ");
        conta = scan.nextInt();
        System.out.println("Informe seu Cpf: ");
        cp = scan.nextLine();
        System.err.println("Deseja depositar durante a criação? se não digite zero, se sim informe o valor: ");
        sal = scan.nextDouble();


        contas[cont] = new Conta(conta, cp, sal);

        scan.close();
    }


    public void salvar(){

        try(FileWriter write = new FileWriter(arquivoNome)){
            for(Conta conta: contas){
                write.append(conta.getNumeroConta() + ";" + conta.getCpf() + ";" + conta.getSaldo());
            }
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo informado não encontrado!");
        }catch(Exception e2){
            System.out.println("Algo deu errado ao tentar escrever o arquivo!");
        }
        
    }



    
}
