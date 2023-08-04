package br.edu.cesarschool.next.oo.negocio;

import br.edu.cesarschool.next.oo.entidade.ContaCorrente;

import java.util.Comparator;

public class ComparadorContaCorrenteSaldo implements Comparator<ContaCorrente> {
    @Override
    public int compare(ContaCorrente cc1, ContaCorrente cc2) {
        if(cc1.getSaldo() > cc2.getSaldo()){
            return 1;
        }
        else if(cc1.getSaldo() == cc2.getSaldo()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
