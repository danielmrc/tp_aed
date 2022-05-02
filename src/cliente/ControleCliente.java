package cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import lista.ListaConta;

public class ControleCliente {

    private String arquivoNome;

    private int cont = 0;

    public Cliente[] chargeClientes(String path){
        Cliente[] clientes = null;

        String line;
        File file = new File(path);
        String[] breakString = new String[2];
        int numeroClientes;

        arquivoNome = path;

        try(Scanner scan = new Scanner(file)){
            numeroClientes = Integer.parseInt(scan.nextLine());
            clientes = new Cliente[numeroClientes + 30];
            while(scan.hasNextLine()){
                line = scan.nextLine();
                if(!line.isBlank() || !line.equals("")){
                    breakString = line.split(";");
                    clientes[cont] = new Cliente(
                        breakString[0],
                        breakString[1]
                    );
                    
                    cont++;
                }
            }            
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!!");
        }catch(Exception e){
            e.printStackTrace();
        }
    return clientes;
}
    
    public void relacionaContas(ListaConta contas, Cliente[] clientes){
        for(var cliente: clientes){
            if(cliente != null)
                cliente.setContas(contas.buscaContasCliente(cliente.getCpf()));
        }
    }

}
