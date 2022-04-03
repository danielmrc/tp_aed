import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Random;

public class ManageAccounts {

    private Random sorteio = new Random(System.currentTimeMillis());

    private String arquivoNome = null;

    private int cont = 0;

    public Conta[] charge(String path){
        Conta[] contas = null;

        String line;
        File file = new File(path);
        String[] breakString = new String[3];
        int numeroContas;

        arquivoNome = path;

        try(Scanner scan = new Scanner(file)){
            numeroContas = Integer.parseInt(scan.next());
            contas = new Conta[numeroContas +50];
            while(scan.hasNextLine()){
                line = scan.next();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    contas[cont] = new Conta(
                        Integer.parseInt(breakString[0]),
                        breakString[1],
                        Double.parseDouble(breakString[2])
                    );
                    
                    cont++;
                }
            }            
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e){
            e.printStackTrace();
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


    public void criarConta(Conta[] contas, int conta, String cp, double sal){     
        contas[cont] = new Conta(conta, cp, sal);
        cont++;
    }


    public void salvar(Conta[] contas){

        try(FileWriter write = new FileWriter(arquivoNome)){
            write.write(cont + "\n");
            for(int i = 0; i < cont -1; i++){
                if(contas[i] != null)
                    write.write(contas[i].getNumeroConta() + ";" + contas[i].getCpf() + ";" + contas[i].getSaldo() + "\n");
            }
            write.write(contas[cont -1].getNumeroConta() + ";" + contas[cont -1].getCpf() + ";" + contas[cont -1].getSaldo());
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo informado não encontrado!");
        }catch(Exception e2){
            e2.printStackTrace();
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


    public Conta buscaConta(Conta[] contas, int num){
        Conta conta = null;

        for(var con: contas){
            if(con != null && con.getNumeroConta() == num)
                conta = con;
        }

        return conta;
    }
    
}
