import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import banco.Conta;
import hash.HashConta;
import cliente.Cliente;
import banco.Operacao;
import hash.Hash;
import data.Data;
import fila.FilaOperacao;


public class ControlerGeral {

    public static int contCliente = 0;

    public static Hash operacoes;

    public static HashConta contasHash;

    public Cliente[] initClientes(String[] nomeArquivo){
        Cliente[] clientes = null;

        initOperacoes(nomeArquivo[2]);
        initContas(nomeArquivo[0]);
        clientes = initClientes(nomeArquivo[1]);

        for(var cliente: clientes){
            if(cliente != null && cliente.getContas() != null)
                cliente.getContas().executarOperacoes();
        }


        return clientes;
    }

    public static int getQuantidadeClientes(){
        return contCliente;
    }

    private Cliente[] initClientes(String path){
        System.out.println("Aguarde, estamos carregando o arquivo de clientes...");
        Cliente[] clientes = null;

        String line;
        File file = new File(path);
        String[] breakString = new String[2];
        int numeroClientes;

        try(Scanner scan = new Scanner(file)){
            numeroClientes = Integer.parseInt(scan.nextLine());
            clientes = new Cliente[numeroClientes + 30];
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    String cpf = breakString[0];
                    clientes[contCliente] = new Cliente(
                        cpf,
                        breakString[1],
                        contasHash.retirar(cpf)
                    );
                    
                    contCliente++;
                }
            }            
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Arquivo de clientes carregado!!");
        return clientes;
    }

    private void initContas(String path){
        System.out.println("Aguarde, estamos carregando o arquivo de contas...");

        String line;
        File file = new File(path);
        String[] breakString = new String[3];

        try(Scanner scan = new Scanner(file)){
            contasHash = new HashConta(Integer.parseInt(scan.next()) + 10000);
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    int conta = Integer.parseInt(breakString[0]);
                    String cpf = breakString[1];
                    FilaOperacao filaOperacoesDaConta = operacoes.retirar(conta);
                    Conta novaConta = new Conta(
                        conta,
                        cpf,
                        Double.parseDouble(breakString[2]),
                        filaOperacoesDaConta
                    );
                    contasHash.inserir(cpf, novaConta);
                }
            }            
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e2){
            e2.printStackTrace();
        }

        System.out.println("Arquivo de contas carregado!!");
    }

    private void initOperacoes(String path){
        System.out.println("Aguarde, estamos carregando o arquivo de operações...");

        Data data;

        String line;
        File file = new File(path);
        String[] breakString = new String[4];
        String[] breakData = new String[3];
        int numeroOperacoes;

        try(Scanner scan = new Scanner(file)){
            numeroOperacoes = Integer.parseInt(scan.next());
            operacoes = new Hash(numeroOperacoes + 1000);
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    breakData = breakString[3].split("/");
                    data = new Data(Integer.parseInt(breakData[0]), Integer.parseInt(breakData[1]), Integer.parseInt(breakData[2]));
                    Operacao op = new Operacao(
                        Integer.parseInt(breakString[0]),
                        Integer.parseInt(breakString[1]),
                        Double.parseDouble(breakString[2]),
                        data                        
                    );
                    operacoes.inserir(op.getNumConta(), op);
                    
                }
            }            
        }catch(FileNotFoundException e1){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e2){
            e2.printStackTrace();
        }
        System.out.println("Arquivo de operações carregado!!");
    }
    
}
