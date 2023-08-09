package br.edu.cesarschool.next.oo.entidade;

public abstract class Conta extends RegistroIdentificavel{

    private static final long serialVersionUID = 1L;

    private double saldo;

    public abstract double obterAliquotaCpmf();

    public void debitar(double valor){

        saldo -= valor*(1+obterAliquotaCpmf());
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
