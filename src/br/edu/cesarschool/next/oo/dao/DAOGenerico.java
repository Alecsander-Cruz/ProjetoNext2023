package br.edu.cesarschool.next.oo.dao;

import br.edu.cesarschool.next.oo.entidade.RegistroIdentificavel;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

import java.io.Serializable;


public class DAOGenerico {
    private CadastroObjetos cadastro;
    private Class tipo;


    public DAOGenerico(Class tipo){
        this.tipo = tipo;
        cadastro = new CadastroObjetos(tipo);
    }

    public boolean incluir(RegistroIdentificavel reg){
        RegistroIdentificavel regBusca = buscar(reg.obterChave());
        if(regBusca != null){
            return false;
        }
        else{
            cadastro.incluir(reg, reg.obterChave());
            return true;
        }
    }

    public boolean alterar(RegistroIdentificavel reg){
        RegistroIdentificavel regBusca = buscar(reg.obterChave());
        if(regBusca == null){
            return false;
        }
        else{
            cadastro.alterar(reg, reg.obterChave());
            return true;
        }
    }

    public boolean excluir(String chave){
        RegistroIdentificavel regBusca = buscar(chave);
        if(regBusca == null){
            return false;
        }
        else{
            cadastro.excluir(chave);
            return true;
        }
    }

    public RegistroIdentificavel buscar(String chave){
        return (RegistroIdentificavel) cadastro.buscar(chave);
    }

    public RegistroIdentificavel[] buscarTodos(){
        Serializable[] rets = cadastro.buscarTodos(tipo);
        RegistroIdentificavel[] registros = new RegistroIdentificavel[rets.length];
        for(int i = 0; i < rets.length; i++){
            registros[i] = (RegistroIdentificavel) rets[i];
        }
        return registros;
    }
}
