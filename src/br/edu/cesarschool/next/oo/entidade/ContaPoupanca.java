package br.edu.cesarschool.next.oo.entidade;

public class ContaPoupanca extends ContaCorrente{

    private double percentualBonus;

    @Override
    public void creditar(double valor){
        double valorBonus = (valor * (1+percentualBonus/100));
        super.creditar(valorBonus);
    }

    @Override
    public double obterAliquotaCpmf() {
        return 0;
    }

    @Override
    public String toString() {
        String retorno = String.format("Percentual de Bonus:      %.2f", percentualBonus);
        return super.toString() + retorno + "%\n";
    }

    public ContaPoupanca() {
    }

    public ContaPoupanca(String numero, double saldo, String nomeCorrentista, double percentualBonus) {
        super(numero, saldo, nomeCorrentista);
        this.percentualBonus = percentualBonus;
    }

    public double getPercentualBonus() {
        return percentualBonus;
    }

    public void setPercentualBonus(double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }
}
