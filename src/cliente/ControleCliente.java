package cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import banco.Conta;
import exception.NotFoundAccountException;

import lista.ListaConta;

public class ControleCliente {


    private int cont = 0;

    public int getCont(){
        return this.cont;
    }

    public Cliente[] chargeClientes(String path){
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

        System.out.println("Arquivo de clientes carregado!!");
    return clientes;
}
    
    public void relacionaContas(ListaConta contas, Cliente[] clientes){
        System.out.println("Aguarde, relacionando contas aos clientes...");
        for(var cliente: clientes){
            if(cliente != null)
                cliente.setContas(contas.buscaContasCliente(cliente.getCpf()));
        }
        System.out.println("Relação de contas com clientes finalizada!!");
    }

    public void maiorSaldo(Cliente[] clientes){
        double maior = 0;
        Conta aux = null;

        for(var cliente: clientes){
            if(cliente != null){
                if(cliente.getContas() != null && cliente.getContas().getContaMaiorSaldo() != null){
                    if(cliente.getContas().getContaMaiorSaldo().getSaldo() > maior){
                        maior = cliente.getContas().getContaMaiorSaldo().getSaldo();
                        aux = cliente.getContas().getContaMaiorSaldo();
                    }
                }
            }
        }

        System.out.println("Numero da conta: " + aux.getNumeroConta());
        System.out.println("Saldo R$" + aux.getSaldo());

    }

    public void saldoMedio(Cliente[] clientes){
        double saldoTotal = 0;
        int contCliente = 0;

        for(var cliente: clientes){
            if(cliente != null){
                if(cliente.getContas() != null){
                    saldoTotal += cliente.getContas().somaSaldoDasContas();
                    contCliente++;
                }
            }
        }

        System.out.println("O saldo médio dos clientes é: R$" + (saldoTotal / contCliente));
    }

    public void realizarDeposito(String cpf, int numConta, double valor, Cliente[] clientes){
        for(var cliente: clientes){
            if(cliente != null)
                if(cliente.getCpf().equals(cpf)){
                    try{
                        cliente.getContas().depositar(cpf, numConta, valor);
                        System.out.println("Depósito realizado com sucesso!");
                    }catch(NotFoundAccountException e2){
                        System.out.println(e2.getMessage());
                    }catch(Exception e2){
                        System.out.println("Ocorreu uma falha ao tentar realizar o depósito! " + e2.getMessage());
                    }
                }
        }
    }

    public void realizarSaque(String cpf, int numConta, double valor, Cliente[] clientes){
        for(var cliente: clientes){
            if(cliente != null)
                if(cliente.getCpf().equals(cpf)){
                    try{
                        cliente.getContas().sacar(cpf, numConta, valor);
                        System.out.println("Saque realizado com sucesso!");
                    }catch(NotFoundAccountException e2){
                        System.out.println(e2.getMessage());
                    }catch(Exception e2){
                        System.out.println("Ocorreu uma falha ao tentar realizar o saque! " + e2.getMessage());
                    }
                }
        }
    }


    public void clientesMaioresSaldos(Cliente[] clientes){
        Cliente[] dezMaioresSaldos = new Cliente[10];
        int cont = 0;

        for(var cliente: clientes){
            if(cliente != null && cliente.getContas() != null){
                if(cont < 10){
                    dezMaioresSaldos[cont] = cliente;
                }else{
                    int aux = Integer.MAX_VALUE;
                    for(int i = 0; i < 10; i++){
                        if(cliente.getContas()!= null && cliente.getContas().somaSaldoDasContas() 
                        > dezMaioresSaldos[i].getContas().somaSaldoDasContas())
                            aux = i;
                        }
                        if(aux != Integer.MAX_VALUE)
                            dezMaioresSaldos[aux] = cliente;
                    }
                cont++;
            }
        }

        System.out.println("Os dez clientes com maior saldo são: ");
        for(int i = dezMaioresSaldos.length -1;i >= 0; i--){
            System.out.println(dezMaioresSaldos[i].getNome());
            System.out.println("Saldo total R$" + dezMaioresSaldos[i].getContas().somaSaldoDasContas());
            System.out.println("-------------------------------------------");
        }

    }
}
