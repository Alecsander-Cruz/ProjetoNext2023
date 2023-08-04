package br.edu.cesarschool.next.oo.entidade;

public class ContaCorrente extends RegistroIdentificavel {
    private static final long serialVersionUID = 1L;
    private String numero;
    private double saldo;
    private String nomeCorrentista;

    public void creditar(double valor){
        this.saldo += valor;
    }

    public void debitar(double valor){
        this.saldo -= valor;
    }

    @Override
    public String obterChave() {
        return numero;
    }

    @Override
    public String toString() {
        String retorno = String.format("""
                Número da conta:          %s
                Saldo:                 R$ %.2f
                Nome do Titular:          %s
                """, numero, saldo, nomeCorrentista);


        String retornoData = String.format("Data de criação da Conta: %d/%d/%d \n",
                this.getDataHoraCriacao().getDayOfMonth(),
                this.getDataHoraCriacao().getMonthValue(),
                this.getDataHoraCriacao().getYear()
                );

        return retorno + retornoData;
    }

    public ContaCorrente() {
    }

    public ContaCorrente(String numero, double saldo, String nomeCorrentista) {
        this.numero = numero;
        this.saldo = saldo;
        this.nomeCorrentista = nomeCorrentista;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }


}
