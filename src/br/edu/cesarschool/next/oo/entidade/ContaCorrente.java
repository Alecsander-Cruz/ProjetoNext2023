package br.edu.cesarschool.next.oo.entidade;

public class ContaCorrente extends Conta {
    private static final long serialVersionUID = 1L;
    private String numero;
    private String nomeCorrentista;

    public void creditar(double valor){
        this.setSaldo(valor);
    }

    @Override
    public String obterChave() {
        return numero;
    }

    @Override
    public double obterAliquotaCpmf() {
        return 0.30;
    }

    @Override
    public String toString() {
        String retorno = String.format("""
                Número da conta:          %s
                Saldo:                 R$ %.2f
                Nome do Titular:          %s
                """, numero, this.getSaldo(), nomeCorrentista);


        String retornoData = String.format("""
                        Data de criação da Conta: %d/%d/%d
                        Dias de conta criada:     %d
                        """,
                this.getDataHoraCriacao().getDayOfMonth(),
                this.getDataHoraCriacao().getMonthValue(),
                this.getDataHoraCriacao().getYear(),
                this.obterTempoDeCriacao()
                );

        return retorno + retornoData;
    }

    public ContaCorrente() {
    }

    public ContaCorrente(String numero, double saldo, String nomeCorrentista) {
        this.numero = numero;
        this.setSaldo(saldo);
        this.nomeCorrentista = nomeCorrentista;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeCorrentista() {
        return nomeCorrentista;
    }

    public void setNomeCorrentista(String nomeCorrentista) {
        this.nomeCorrentista = nomeCorrentista;
    }


}
