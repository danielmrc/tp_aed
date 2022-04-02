import java.io.FileWriter;
import java.io.IOException;


public class GeraArquivo {
    
    public void gerar(String arquivo){
        int conta = 50050;
        String cpf = "892094834";
        double saldo = 5300.92;
        int verificador = 00;


        try(FileWriter escreve = new FileWriter(arquivo)){
            escreve.write("100 \n");
            for(int i = 0; i < 99; i++){
                escreve.write(conta + ";" + cpf + String.valueOf(verificador) + ";" + saldo + "\n");
                conta = conta +4;
                saldo = saldo + 300.00;
                verificador++;
            }
            escreve.write(conta + ";" + cpf + String.valueOf(verificador) + ";" + saldo);
        }catch(IOException e){
            System.out.println("Erro ao tentar criar arquivo!");
        }

 
    }



}
