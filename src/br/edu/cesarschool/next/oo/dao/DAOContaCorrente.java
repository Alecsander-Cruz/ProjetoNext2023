package br.edu.cesarschool.next.oo.dao;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;
import br.edu.cesarschool.next.oo.entidade.RegistroIdentificavel;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;


public class DAOContaCorrente {
    private CadastroObjetos cadastro = new CadastroObjetos(ContaCorrente.class);
    private DAOGenerico daoGenerico = new DAOGenerico(ContaCorrente.class);

    public boolean incluir(ContaCorrente conta){
        return daoGenerico.incluir(conta);
    }

    public boolean alterar(ContaCorrente conta){
        return daoGenerico.alterar(conta);
    }

    public boolean excluir(String numero){
        return daoGenerico.excluir(numero);
    }

    public ContaCorrente buscar(String numero){
        return (ContaCorrente) daoGenerico.buscar(numero);
    }

    public ContaCorrente[] buscarTodos(){

        RegistroIdentificavel[] contas =  daoGenerico.buscarTodos();
        ContaCorrente[] contasReg = new ContaCorrente[contas.length];
        for(int i = 0; i < contas.length; i++){
            contasReg[i] = (ContaCorrente) contas[i];
        }

        return contasReg;
    }
}
