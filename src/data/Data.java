package data;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    private static int[] dias = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};


    public Data(){
        this.dia = 01;
        this.mes = 01;
        this.ano = 1900;
    }

    public Data(int dia, int mes, int ano){
        if(dias[dia] >= dia)
            this.dia = dia;
            else
                System.out.println("Dia inválido para o mês!!");
        if(mes <= 12)
            this.mes = mes;
            else
                System.out.println("Mês inválido!!");
        if(ano > 1900)
            this.ano = ano;
            else
                System.out.println("Ano não suportado!!");
    }

    public String getData(){
        return dia + "/" + mes + "/" + ano;
    }

    public int getDia(){
        return this.dia;
    }

    public int getMes(){
        return this.mes;
    }
    
    public int getAno(){
        return this.ano;
    }

    public void setDia(int dia){
        this.dia = dia;
    }

    public void setMes(int mes){
        this.mes = mes;
    }

    public void setAno(int ano){
        this.ano = ano;
    }
}
