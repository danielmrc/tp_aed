import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;

public class LoadingAccounts {

    private Random sorteio = new Random(43);

    private String arquivoNome = null;

    private int cont = 0;

    public Conta[] charge(String path){
        Conta[] contas = null;

        File file = new File(path);
        String[] breakString = new String[3];
        int numeroContas;

        arquivoNome = path;

        try(Scanner scan = new Scanner(file)){
            numeroContas = scan.nextInt();
            contas = new Conta[numeroContas +50];
            while(scan.hasNextLine()){
                breakString = scan.next().split(";");
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
    return contas;
}


    public void gerarRelatorio(Conta[] contas){
    
        for(Conta conta: contas){
            if(conta != null){
                System.out.println("Numero conta: " + conta.getNumeroConta());
                System.out.println("Cpf: " + conta.getCpf());
                System.out.println("Saldo: " + conta.getSaldo() + "\n");
            }            
        }
    System.out.println();

    }


    public void criarConta(Conta[] contas){
        int conta;
        String cp;
        double sal;

        Scanner scan = new Scanner(System.in);

        System.out.println("Informe o número da conta: ");
        conta = scan.nextInt();
        System.out.println("Informe seu Cpf: ");
        cp = scan.next();
        System.out.println("Deseja depositar durante a criação? se não digite zero, se sim informe o valor: ");
        sal = scan.nextDouble();


        contas[cont] = new Conta(conta, cp, sal);

        scan.close();
    }


    public void salvar(Conta[] contas){

        try(FileWriter write = new FileWriter(arquivoNome)){
            write.write(contas.length + "\n");
            for(Conta conta: contas){
                write.write(conta.getNumeroConta() + ";" + conta.getCpf() + ";" + conta.getSaldo() + "\n");
            }
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo informado não encontrado!");
        }catch(Exception e2){
            System.out.println("Algo deu errado ao tentar escrever o arquivo!");
        }
        
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



    
}
